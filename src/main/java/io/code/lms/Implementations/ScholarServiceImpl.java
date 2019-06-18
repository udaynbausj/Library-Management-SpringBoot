package io.code.lms.Implementations;

import io.code.lms.Dtos.BulkBookIdRequestDto;
import io.code.lms.Services.ScholarService;
import org.springframework.stereotype.Service;

@Service
public class ScholarServiceImpl implements ScholarService {
    @Override
    public void addBookToScholarAccount(Integer bookId, Integer scholarId) {

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
