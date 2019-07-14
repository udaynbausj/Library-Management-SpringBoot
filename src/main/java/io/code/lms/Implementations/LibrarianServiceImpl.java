package io.code.lms.Implementations;

import io.code.lms.Constants.Constants;
import io.code.lms.Dtos.*;
import io.code.lms.Entities.Book;
import io.code.lms.Entities.BookIdScholarIdMapping;
import io.code.lms.Entities.Scholar;
import io.code.lms.Exceptions.CustomExceptions.*;
import io.code.lms.Exceptions.SQLExceptions.DBExceptionBase;
import io.code.lms.Repositories.BookCrudDao;
import io.code.lms.Repositories.BookIdScholarIdMappingDao;
import io.code.lms.Repositories.ScholarCrudDao;
import io.code.lms.Services.LibrarianService;
import org.apache.log4j.Logger;
import org.apache.log4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.*;
import java.util.function.Consumer;


@Service
public class LibrarianServiceImpl implements LibrarianService {

    @Autowired
    private ScholarCrudDao scholarCrudDao;

    @Autowired
    private BookCrudDao bookCrudDao;

    @Autowired
    private BookIdScholarIdMappingDao bookIdScholarIdMappingDao;

    private static Logger logger = Logger.getLogger(LibrarianServiceImpl.class);

    @Override
    public void addBooks(List<BookDto> bookDtoList) {
        Consumer<BookDto> bookDtoConsumer = bookDto -> {
            Book book = new Book();
            book.setAvailabilityCount(bookDto.getAvailabilityCount());
            book.setPublication(bookDto.getPublication());
            book.setTitle(bookDto.getTitle());
            logger.info("Saving book entity ... " + book.toString());
            bookCrudDao.save(book);
            logger.info("Book saved successfully ");
        };
        bookDtoList.forEach(bookDtoConsumer);
    }

    @Override
    public void deleteBooks(BulkBookIdRequestDto bulkBookIdRequestDto) {
        //while deleting a book , we need to check if this book is associated with any scholar.
        //if this book is associated with any scholar, then deletion cannot be performed
        List<Integer>bookIdList = bulkBookIdRequestDto.getBookIds();
        Consumer<Integer> bookIdConsumer = bookId -> {
            Optional<Book>optional =  (bookCrudDao.findById(bookId));
            if(optional.isPresent()) {
                Optional<List<BookIdScholarIdMapping> >optional1 =
                        Optional.ofNullable(bookIdScholarIdMappingDao.findByBookId(bookId));
                if(optional1.isPresent()) {
                    throw new DependencyException("Book can't be deleted as, it is associated with scholar ");
                }
                logger.info("deleting book-id " + bookId);
                bookCrudDao.deleteById(bookId);
                logger.info("successfully deleted the book ");
            } else {
                logger.error("Book with Id not found " + bookId);
                throw new RecordNotFoundException("Record not found exception");
            }
        };
        bookIdList.forEach(bookIdConsumer);
    }

    @Override
    public Book getBook(BulkBookIdRequestDto bulkBookIdRequestDto) {
        return bookCrudDao.findById(bulkBookIdRequestDto.getBookIds().get(0))
                          .orElseThrow(() -> new RecordNotFoundException("Record not found"));
    }

    @Override
    public List<Book> getAllBooks() {
        List<Book>bookList = bookCrudDao.findAll();
        bookList.forEach(book -> {
            logger.info("Book : " + book);
        });
        return bookList;
    }

    @Override
    public void updateAvailabilityCountOfBook(Integer bookId, Integer count) {
        Optional<Book>optionalBook = bookCrudDao.findById(bookId);
        if(optionalBook.isPresent()) {
            Book book = optionalBook.get();
            Integer availabilityCount = book.getAvailabilityCount();
            availabilityCount += count;
            book.setAvailabilityCount(availabilityCount);
            bookCrudDao.save(book);
        } else {
            throw new RecordNotFoundException("Record not found for given bookId : " + bookId);
        }
    }

    @Override
    public void addScholar(ScholarDto scholarDto) {
        Scholar scholar = new Scholar();
        scholar.setNumOfBooksPresent(Constants.INITIAL_NUMBER_OF_BOOKS_FOR_SCHOLAR);
        scholar.setStatus(Constants.DEFAULT_SCHOLAR_STATUS);
        scholar.setName(scholarDto.getName());
        scholar.setFine(Constants.INITIAL_FINE_FOR_SCHOLAR);
        MDC.put("scholarName" , scholar.getName());
        logger.info("saving scholar entity : " + scholar.toString());
        scholarCrudDao.save(scholar);
        logger.info("scholar entity saved successfully ");
    }

    @Override
    public Map<String ,String > addScholarInBulk(List<ScholarDto> scholarDtoList) throws DBExceptionBase {
        Map<String ,String >result = new HashMap<>();
        scholarDtoList.forEach(this::addScholar);
        result.put("status","success");
        return result;
    }

    @Override
    public void deleteScholar(Integer scholarId) {
//        scholarCrudDao.findById(scholarId)
//                .ifPresentOrElse(scholar -> {
//                    logger.info("Deleting scholar .. ");
//                    scholarCrudDao.deleteById(scholarId);
//                    logger.info("successfully deleted scholar ");
//                } , () -> new RecordNotFoundException("No scholar found with given id"));
        scholarCrudDao.findById(scholarId).orElseThrow(() -> new RecordNotFoundException("No record found with " +
                "given scholarId "));
    }

    @Override
    public void deleteScholarInBulk(BulkScholarIdRequestDto bulkScholarIdRequestDto) {
        List<Integer> scholarIdList = bulkScholarIdRequestDto.getScholarIds();
        scholarIdList.forEach(this::deleteScholar);
    }

    @Override
    public void issueBookToScholar(Integer bookId,Integer scholarId) {

        if(!bookCrudDao.findById(bookId).isPresent() ) {
            throw new RecordNotFoundException("No book with given id " + bookId);
        }

        if(!scholarCrudDao.findById(scholarId).isPresent()) {
            throw new RecordNotFoundException("No scholar with given id " + scholarId);
        }

        Integer numOfBooksScholarHas = bookIdScholarIdMappingDao.findByScholarId(scholarId).size();
        if (numOfBooksScholarHas == Constants.MAX_BOOK_ISSUE) {
            throw new MaxBooksAlreadyIssuedException("Scholar already have been issued with max no of books ");
        } else {
            if(scholarCrudDao.findById(scholarId).get().getFine() > 0) {
                throw new FineChargeException("Scholar has fine overdue. It should be resolved");
            } else {
                if(bookCrudDao.findById(bookId).get().getAvailabilityCount() > 0) {
                    BookIdScholarIdMapping bookIdScholarIdMapping = new BookIdScholarIdMapping();
                    bookIdScholarIdMapping.setBookId(bookId);
                    bookIdScholarIdMapping.setScholarId(scholarId);
                    bookIdScholarIdMapping.setIssuedOn(new Date());
                    bookIdScholarIdMappingDao.save(bookIdScholarIdMapping);
                }
                this.updateAvailabilityCountOfBook(bookId,-1);
                scholarCrudDao.findById(scholarId).get().setNumOfBooksPresent(++numOfBooksScholarHas);
            }
        }
    }

    @Override
    public void issueBooksToScholarInBulk(BulkBookIdRequestDto bulkBookIdRequestDto, Integer scholarId) {
        Consumer<Integer>bookIdConsumer = bookId -> {
            this.issueBookToScholar(bookId,scholarId);
        };
        bulkBookIdRequestDto.getBookIds().forEach(bookIdConsumer);
    }

    @Override
    public void renewBookScholarRequest(Integer bookId , Integer scholarId) {

        if(!bookCrudDao.findById(bookId).isPresent() ) {
            throw new RecordNotFoundException("No book with given id " + bookId);
        }

        if(!scholarCrudDao.findById(scholarId).isPresent()) {
            throw new RecordNotFoundException("No scholar with given id " + scholarId);
        }
            MDC.put("bookId" , bookId);
            MDC.put("scholarId",scholarId);
            Integer numOfTimesRenewed = bookIdScholarIdMappingDao
                            .findByBookIdAndScholarId(bookId,scholarId).getNumOfTimesRenewed();
            if(numOfTimesRenewed == Constants.MAX_RENEWAL) {
                throw new MaxRenewedBookException("This book has been renewed max no of times");
            } else {
                Float fineOnScholar = scholarCrudDao.findById(scholarId).get().getFine();
                if(fineOnScholar > 0) {
                    throw new FineChargeException("User has been charged fine. Please pay and renew books");
                } else {
                    BookIdScholarIdMapping bookIdScholarIdMapping =
                            bookIdScholarIdMappingDao.findByBookIdAndScholarId(bookId,scholarId);
                    bookIdScholarIdMapping.setNumOfTimesRenewed(Constants.MAX_RENEWAL);
                    logger.info("renewing book for scholar ");
                    bookIdScholarIdMappingDao.save(bookIdScholarIdMapping);
                    logger.info("renewed book successfully ");
                }
            }
    }

    @Override
    public void renewBooksScholarRequestInBulk(List<RenewBookDto> renewBookDtoList) {
        renewBookDtoList.forEach(renewBookDto -> {
            Integer bookId = renewBookDto.getBookId();
            Integer scholarId = renewBookDto.getScholarId();
            this.renewBookScholarRequest(bookId,scholarId);
        });
    }

    @Override
    public void reserveBookScholarRequest(List<ReserveBookDto> reserveBookDtos) {
        
    }

    @Override
    public void reserveBooksScholarRequestInBulk(List<ReserveBookDto> reserveBookDtos) {

    }

    @Override
    public void updateScholarFine(Integer scholarId, Integer amount) {

    }

    @Override
    public void addBook(@Valid BookDto bookDto) {
        Book book = new Book();
        book.setAvailabilityCount(bookDto.getAvailabilityCount());
        book.setTitle(bookDto.getTitle());
        bookCrudDao.save(book);
    }
}
