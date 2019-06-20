package io.code.lms.Exceptions.CustomExceptions;

import io.code.lms.Exceptions.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<?> handleAllExceptions(Exception exe, WebRequest webRequest) {
        List<String > details = new ArrayList<>();
        details.add(exe.getLocalizedMessage());
        ErrorResponse errorResponse = new ErrorResponse("Server error",details);
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(RecordNotFoundException.class)
    public final ResponseEntity<?> handleRecordNotFoundExceptions(RecordNotFoundException exc,
                                                                 WebRequest  webRequest) {
        List<String >errorDetails = new ArrayList<>();
        errorDetails.add(exc.getLocalizedMessage());
        ErrorResponse errorResponse = new ErrorResponse("Record Not found " , errorDetails);
        return new ResponseEntity<>(errorResponse,HttpStatus.NOT_FOUND);
    }
}
