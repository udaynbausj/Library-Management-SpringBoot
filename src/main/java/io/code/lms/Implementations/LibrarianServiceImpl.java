package io.code.lms.Implementations;

import io.code.lms.Dtos.*;
import io.code.lms.Entities.Book;
import io.code.lms.Entities.BookIdScholarIdMapping;
import io.code.lms.Entities.Scholar;
import io.code.lms.Exceptions.CustomExceptions.DependencyException;
import io.code.lms.Exceptions.CustomExceptions.RecordNotFoundException;
import io.code.lms.Exceptions.SQLExceptions.DBExceptionBase;
import io.code.lms.Repositories.BookCrudDao;
import io.code.lms.Repositories.BookIdScholarIdMappingDao;
import io.code.lms.Repositories.ScholarCrudDao;
import io.code.lms.Services.LibrarianService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
                Optional<BookIdScholarIdMapping>optional1 =
                        Optional.ofNullable(bookIdScholarIdMappingDao.findByBookId(bookId));
                if(optional1.isPresent()) {
                    throw new DependencyException("Book can't be deleted as, it is associated with scholar : "
                            + optional1.get().getScholarId());
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
            book.setAvailabilityCount(count);
            bookCrudDao.save(book);
        } else {
            throw new RecordNotFoundException("Record not found for given bookId : " + bookId);
        }
    }

    @Override
    public Map<String ,String > addScholar(List<ScholarDto> scholarDtoList) throws DBExceptionBase {
        Map<String ,String >result = new HashMap<>();
        Iterator iterator = scholarDtoList.iterator();
        while(iterator.hasNext()) {
            ScholarDto scholarDto = (ScholarDto) iterator.next();
            Scholar scholar = new Scholar();
            scholar.setName(scholarDto.getName());
            scholar.setStatus(scholarDto.getStatus());
            logger.info("Saving scholar entity :  " + scholar.toString());
            try {
                Scholar scholarRecord = scholarCrudDao.save(scholar);
                if(null == scholarRecord)
                    throw new DBExceptionBase("Exception while saving Entity to DB");
            } catch (DBExceptionBase sqle) {
                logger.error("Exception while saving entity to DB: "
                        + sqle.getLocalizedMessage());
                throw sqle;
            }
            logger.info("Successfully saved into db");
        }
        result.put("status","success");
        return result;
    }

    @Override
    public void deleteScholar(BulkScholarIdRequestDto bulkScholarIdRequestDto) {
        List<Integer> scholarIdList = bulkScholarIdRequestDto.getScholarIds();
        //you cannot delete a scholar if this scholar has any book with him.
        //check if this scholar has ansy bookId associated with him
        Consumer<Integer> scholarIdConsumer = scholarId -> {
            Optional<BookIdScholarIdMapping>optional = Optional.ofNullable(
                    bookIdScholarIdMappingDao.findByScholarId(scholarId));
            if(optional.isPresent()) {
                throw new DependencyException("Scholar cannot be deleted as a book with id : {} is " +
                        "associated with him" + optional.get().getBookId());
            }
            logger.info("Deleting scholar .. ");
            scholarCrudDao.deleteById(scholarId);
            logger.info("successfully deleted scholar ");
        };
        scholarIdList.forEach(scholarIdConsumer);
    }

    @Override
    public void issueBookToScholar(Integer bookId) {

    }

    @Override
    public void issueBooksToScholarInBulk(BulkBookIdRequestDto bulkBookIdRequestDto) {

    }

    @Override
    public void renewBookScholarRequest(List<RenewBookDto> renewBookDtoList) {

    }

    @Override
    public void renewBooksScholarRequestInBulk(List<RenewBookDto> renewBookDtoList) {

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
}
