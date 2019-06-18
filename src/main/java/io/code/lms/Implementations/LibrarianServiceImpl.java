package io.code.lms.Implementations;

import io.code.lms.Dtos.*;
import io.code.lms.Services.LibrarianService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibrarianServiceImpl implements LibrarianService {
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
    public void addScholar(List<ScholarDto> scholarDtoList) {

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
