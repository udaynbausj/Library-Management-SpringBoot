package io.code.lms.Controllers;

import io.code.lms.Dtos.BulkScholarIdRequestDto;
import io.code.lms.Dtos.ScholarDto;
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
    public ResponseEntity<?> addScholar(@RequestBody List<ScholarDto> scholarDtoList) {
        ResponseEntity responseEntity = null;
        try {
            responseEntity = new ResponseEntity(librarianService.addScholar(scholarDtoList),
                                                                            HttpStatus.OK);
        } catch (DBExceptionBase db) {
            responseEntity = new ResponseEntity(db.getLocalizedMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    public ResponseEntity<?> deleteScholar(@RequestBody Integer scholarId) {
        ResponseEntity<?>responseEntity = null;
        try {
            responseEntity = new ResponseEntity<>(librarianService.deleteScholar(););
        }
    }
}
