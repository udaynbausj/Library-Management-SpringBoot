package io.code.lms.Services;

import io.code.lms.Dtos.BulkBookIdRequestDto;

public interface ScholarService {
    void addBookToScholarAccount(Integer bookId, Integer scholarId);
    void addBooksToScholarAccountInBulk(Integer scholarId , BulkBookIdRequestDto bulkBookIdRequestDto);
    void returnBooksInBulk(Integer scholarId, BulkBookIdRequestDto bulkBookIdRequestDto);
    void returnBook(Integer bookId, Integer scholarId);
    void payFine(Integer scholarId, Integer amount);
    void renewBooksInBulk(Integer scholarId, BulkBookIdRequestDto bulkBookIdRequestDto);
    void renewBook(Integer bookId, Integer scholarId);
    void getScholarInfo();
    void getAllScholarInfo();
}
