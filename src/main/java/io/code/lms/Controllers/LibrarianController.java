package io.code.lms.Controllers;

import io.code.lms.Dtos.BookDto;
import io.code.lms.Dtos.BulkScholarIdRequestDto;
import io.code.lms.Dtos.ScholarDto;
import io.code.lms.Exceptions.CustomExceptions.CustomRuntimeException;
import io.code.lms.Exceptions.SQLExceptions.DBExceptionBase;
import io.code.lms.Implementations.LibrarianServiceImpl;
import io.code.lms.Routes.LibrarianRoutes;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController(LibrarianRoutes.librarianBaseUrl)
public class LibrarianController {

    @Autowired
    private LibrarianServiceImpl librarianService;

    @ApiOperation(value = "adding scholar into Library Database")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "success in saving to db",response = Map.class),
            @ApiResponse(code = 500, message = "Internal server error ",response = Map.class)})
    @PostMapping(value = LibrarianRoutes.librarianScholarCrud)
    public ResponseEntity<?> addScholar(@RequestBody ScholarDto scholarDto) {
        ResponseEntity responseEntity = null;
        try {
            librarianService.addScholar(scholarDto);
            responseEntity = new ResponseEntity<>("status",HttpStatus.OK);
        } catch (CustomRuntimeException cre) {
            responseEntity = new ResponseEntity<>(cre.getLocalizedMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @ApiOperation(value = "delete a scholar")
    @ApiResponses(value = {@ApiResponse(code = 200,message = "success in deleting scholar",
                                        response = Map.class)})
    @DeleteMapping(value = LibrarianRoutes.librarianScholarCrud)
    public ResponseEntity<?> deleteScholar(@RequestBody Integer scholarId) {
        ResponseEntity responseEntity = null;
        try {
            librarianService.deleteScholar(scholarId);
            responseEntity = new ResponseEntity("status",HttpStatus.OK);
        } catch (CustomRuntimeException cre) {
            responseEntity = new ResponseEntity (cre.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @ApiOperation(value = "Add book to library")
    @ApiResponses(value = {@ApiResponse(code = 200,message = "Success in saving book to library",
                            response = String.class)})
    @PostMapping(value = LibrarianRoutes.librarianBookCrud)
    public ResponseEntity<?> addBookToLibrary(@Valid @RequestBody BookDto bookDto) {
        ResponseEntity responseEntity = null;
        try {
            librarianService.addBook(bookDto);
            responseEntity = new ResponseEntity("Status",HttpStatus.OK);
        } catch (CustomRuntimeException cre) {
            responseEntity = new ResponseEntity(cre.getStackTrace(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }


}
