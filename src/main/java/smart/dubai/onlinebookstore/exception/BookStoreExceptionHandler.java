package smart.dubai.onlinebookstore.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class BookStoreExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object> resourceNotFoundException(ResourceNotFoundException ex,WebRequest request) {
    	ErrorResponse errorDetails = new ErrorResponse(ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<Object>( errorDetails, HttpStatus.BAD_REQUEST);
    }
    
}
