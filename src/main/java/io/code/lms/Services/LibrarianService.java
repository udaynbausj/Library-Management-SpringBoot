package io.code.lms.Services;

import io.code.lms.Dtos.*;
import io.code.lms.Entities.Book;

import java.util.List;
import java.util.Map;

public interface LibrarianService {
    void addBooks(List<BookDto>bookDtoList) ;
    void deleteBooks(BulkBookIdRequestDto bulkBookIdRequestDto);
    Book getBook(BulkBookIdRequestDto bulkBookIdRequestDto);
    List<Book> getAllBooks();
    void updateAvailabilityCountOfBook(Integer bookId,Integer count);
    Map<String ,String > addScholarInBulk(List<ScholarDto> scholarDtoList) throws Exception;
    void addScholar(ScholarDto scholarId);
    void deleteScholarInBulk(BulkScholarIdRequestDto bulkScholarIdRequestDto);
    void deleteScholar(Integer scholarId);
    void issueBookToScholar(Integer bookId , Integer scholarId);
    void issueBooksToScholarInBulk(BulkBookIdRequestDto bulkBookIdRequestDto, Integer scholarId);
    void renewBookScholarRequest(Integer bookId,Integer scholarId);
    void renewBooksScholarRequestInBulk(List<RenewBookDto>renewBookDtoList);
    void reserveBookScholarRequest(List<ReserveBookDto>reserveBookDtos);
    void reserveBooksScholarRequestInBulk(List<ReserveBookDto>reserveBookDtos);
    void updateScholarFine(Integer scholarId,Integer amount);
    void addBook(BookDto bookDto);
}
