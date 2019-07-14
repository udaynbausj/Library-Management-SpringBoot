package io.code.lms.Implementations;

import io.code.lms.Constants.Constants;
import io.code.lms.Dtos.BulkBookIdRequestDto;
import io.code.lms.Entities.Book;
import io.code.lms.Entities.BookIdScholarIdMapping;
import io.code.lms.Exceptions.CustomExceptions.FineChargeException;
import io.code.lms.Exceptions.CustomExceptions.MaxBooksAlreadyIssuedException;
import io.code.lms.Exceptions.CustomExceptions.RecordNotFoundException;
import io.code.lms.Repositories.BookCrudDao;
import io.code.lms.Repositories.BookIdScholarIdMappingDao;
import io.code.lms.Repositories.ScholarCrudDao;
import io.code.lms.Services.ScholarService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.function.Consumer;

@Service
public class ScholarServiceImpl implements ScholarService {


    @Autowired
    private BookCrudDao bookCrudDao;

    @Autowired
    private ScholarCrudDao scholarCrudDao;

    @Autowired
    private BookIdScholarIdMappingDao bookIdScholarIdMappingDao;

    private static Logger logger = Logger.getLogger(ScholarServiceImpl.class);

    @Override
    public void addBookToScholarAccount(Integer bookId, Integer scholarId) {
        //check if book with bookId and scholar with scholarId are present
        bookCrudDao.findById(bookId)
                .orElseThrow(() -> new RecordNotFoundException("Book with given id not found"));
        scholarCrudDao.findById(scholarId).ifPresentOrElse(
                scholar -> {
                    if (scholar.getNumOfBooksPresent() > 4 ) {
                        throw new MaxBooksAlreadyIssuedException("Max books already issued .");
                    } else if (scholar.getFine() != Constants.INITIAL_FINE_FOR_SCHOLAR) {
                        throw new FineChargeException(" Fine is not cleared yet ");
                    } else {
                        Integer numberOfBooksPresent = scholar.getNumOfBooksPresent();
                        scholar.setNumOfBooksPresent(numberOfBooksPresent++);
                    }
                    Consumer<Book>bookConsumer = book -> {
                        Integer presentBooksAtLib = book.getAvailabilityCount() - 1;
                        book.setAvailabilityCount(presentBooksAtLib);
                    };
                    bookCrudDao.findById(bookId).ifPresent(bookConsumer);
                    scholarCrudDao.save(scholar);
                    BookIdScholarIdMapping bookIdScholarIdMapping = new BookIdScholarIdMapping();
                    bookIdScholarIdMapping.setScholarId(scholarId);
                    bookIdScholarIdMapping.setBookId(bookId);
                    bookIdScholarIdMapping.setIssuedOn(new Date());
                    bookIdScholarIdMappingDao.save(bookIdScholarIdMapping);
                } , () -> {
                    new RecordNotFoundException("Scholar with given id is not found");
                }
        );
        //check if scholar has more than 4 books available
        BookIdScholarIdMapping bookIdScholarIdMapping = new BookIdScholarIdMapping();
        bookIdScholarIdMapping.setBookId(bookId);
        bookIdScholarIdMapping.setScholarId(scholarId);
        bookIdScholarIdMappingDao.save(bookIdScholarIdMapping);
    }

    @Override
    public void addBooksToScholarAccountInBulk(Integer scholarId, BulkBookIdRequestDto bulkBookIdRequestDto) {

    }

    @Override
    public void returnBooksInBulk(Integer scholarId, BulkBookIdRequestDto bulkBookIdRequestDto) {

    }

    @Override
    public void returnBook(Integer bookId, Integer scholarId) {

    }

    @Override
    public void payFine(Integer scholarId, Integer amount) {

    }

    @Override
    public void renewBooksInBulk(Integer scholarId, BulkBookIdRequestDto bulkBookIdRequestDto) {

    }

    @Override
    public void renewBook(Integer bookId, Integer scholarId) {

    }

    @Override
    public void getScholarInfo() {

    }

    @Override
    public void getAllScholarInfo() {

    }
}
