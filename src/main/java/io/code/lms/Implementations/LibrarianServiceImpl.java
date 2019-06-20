package io.code.lms.Implementations;

import io.code.lms.Dtos.*;
import io.code.lms.Entities.Scholar;
import io.code.lms.Exceptions.SQLExceptions.DBExceptionBase;
import io.code.lms.Repositories.ScholarCrudDao;
import io.code.lms.Services.LibrarianService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class LibrarianServiceImpl implements LibrarianService {

    @Autowired
    private ScholarCrudDao scholarCrudDao;

    private static Logger logger = Logger.getLogger(LibrarianServiceImpl.class);

    @Override
    public void addBooks(List<BookDto> bookDtoList) {

    }

    @Override
    public void deleteBooks(BulkBookIdRequestDto bulkBookIdRequestDto) {

    }

    @Override
    public void getBook(BulkBookIdRequestDto bulkBookIdRequestDto) {

    }

    @Override
    public void getAllBooks() {

    }

    @Override
    public void updateAvailabilityCountOfBook(Integer bookId, Integer count) {

    }

    @Override
    public Map<String ,String > addScholar(List<ScholarDto> scholarDtoList) throws DBExceptionBase {
        Iterator iterator = scholarDtoList.iterator();
        Map<String ,String >returnMap = new HashMap<>();
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
            returnMap.put("status" , "success");
            logger.info("Successfully saved into db");
        }
        return returnMap;
    }

    @Override
    public void deleteScholar(BulkScholarIdRequestDto bulkScholarIdRequestDto) {

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
