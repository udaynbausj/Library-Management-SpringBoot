package io.code.lms.Controllers;

import io.code.lms.Dtos.ScholarDto;
import io.code.lms.Exceptions.CustomScholarException;
import io.code.lms.Implementations.LibrarianServiceImpl;
import io.code.lms.Routes.LibrarianRoutes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController(LibrarianRoutes.librarianBaseUrl)
public class LibrarianController {

    @Autowired
    private LibrarianServiceImpl librarianService;


    @PostMapping(value = LibrarianRoutes.librarianScholarCrud)
    public ResponseEntity<?> addScholar(List<ScholarDto> scholarDtoList) {

    }
}
