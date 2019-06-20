package io.code.lms.Services;

import io.code.lms.Dtos.*;

import java.util.List;
import java.util.Map;

public interface LibrarianService {
    void addBooks(List<BookDto>bookDtoList) ;
    void deleteBooks(BulkBookIdRequestDto bulkBookIdRequestDto);
    void getBook(BulkBookIdRequestDto bulkBookIdRequestDto);
    void getAllBooks();
    void updateAvailabilityCountOfBook(Integer bookId,Integer count);
    Map<String ,String > addScholar(List<ScholarDto> scholarDtoList) throws Exception;
    void deleteScholar(BulkScholarIdRequestDto bulkScholarIdRequestDto);
    void issueBookToScholar(Integer bookId);
    void issueBooksToScholarInBulk(BulkBookIdRequestDto bulkBookIdRequestDto);
    void renewBookScholarRequest(List<RenewBookDto>renewBookDtoList);
    void renewBooksScholarRequestInBulk(List<RenewBookDto>renewBookDtoList);
    void reserveBookScholarRequest(List<ReserveBookDto>reserveBookDtos);
    void reserveBooksScholarRequestInBulk(List<ReserveBookDto>reserveBookDtos);
    void updateScholarFine(Integer scholarId,Integer amount);
}
