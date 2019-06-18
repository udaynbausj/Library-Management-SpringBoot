package io.code.lms.Implementations;

import io.code.lms.Dtos.*;
import io.code.lms.Entities.Scholar;
import io.code.lms.Exceptions.CustomScholarException;
import io.code.lms.Repositories.ScholarCrudDao;
import io.code.lms.Services.LibrarianService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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
    public Map<String ,String > addScholar(List<ScholarDto> scholarDtoList) {
        Iterator iterator = scholarDtoList.iterator();
        Map<String ,String >returnMap = new HashMap<>();
        while(iterator.hasNext()) {
            //process each ScholarDto and call DB
            ScholarDto scholarDto = (ScholarDto) iterator.next();
            Scholar scholar = new Scholar();
            scholar.setName(scholarDto.getName());
            scholar.setStatus(scholarDto.getStatus());
            logger.info("Saving scholar entity :  " + scholar.toString());
            try{
                scholarCrudDao.save(scholar);
                returnMap.put("status" , "success");
                logger.info("Successfully saved into db");
            }catch (CustomScholarException cse){
                logger.error("Error occured while saving scholar entity : " + scholar.toString());
                returnMap.put("status",cse.getErrorCode());
            }
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
