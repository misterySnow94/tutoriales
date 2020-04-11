package Wilson.ProblemWilson.exceptions;
import Wilson.ProblemWilson.exceptions.responses.BadRequestException;
import Wilson.ProblemWilson.exceptions.responses.ErrorDetails;
import Wilson.ProblemWilson.exceptions.responses.FoundException;
import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import Wilson.ProblemWilson.exceptions.responses.NotFoundException;
import Wilson.ProblemWilson.exceptions.responses.Ok;
import Wilson.ProblemWilson.exceptions.responses.ResourcesNotFoundException;
import Wilson.ProblemWilson.exceptions.responses.Unauthorized;

@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
  
  @ExceptionHandler(ResourcesNotFoundException.class)
 public final ResponseEntity<?> resourceNotFoundException(ResourcesNotFoundException ex, WebRequest request) {
  ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
  return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
 }
 
 @ExceptionHandler(Exception.class)
 public final ResponseEntity<?> globleExcpetionHandler(Exception ex, WebRequest request) {
  ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
  return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
 }
  

  @ExceptionHandler(NotFoundException.class)
  public final ResponseEntity<ExceptionResponse> handleNotFoundException(NotFoundException ex, WebRequest request) {
    ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage() ,
        request.getDescription(false));
    return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
  }
  
  @ExceptionHandler(FoundException.class)
  public final ResponseEntity<ExceptionResponse> handleFoundException(FoundException ex, WebRequest request) {
    ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
        request.getDescription(false));
    return new ResponseEntity<>(exceptionResponse, HttpStatus.FOUND);
  }
  
  @ExceptionHandler(BadRequestException.class)
  public final ResponseEntity<ExceptionResponse> handleBadRequestException(BadRequestException ex, WebRequest request) {
    ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
        request.getDescription(false));
    return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
  }
  
  @ExceptionHandler(Ok.class)
  public final ResponseEntity<ExceptionResponse> handleOkException(Ok ex, WebRequest request) {
    ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
        request.getDescription(false));
    return new ResponseEntity<>(exceptionResponse, HttpStatus.OK);
  }
  
  @ExceptionHandler(Unauthorized.class)
  public final ResponseEntity<ExceptionResponse> handleUnauthorizedException(Unauthorized ex, WebRequest request) {
    ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
        request.getDescription(false));
    return new ResponseEntity<>(exceptionResponse, HttpStatus.UNAUTHORIZED);
  }

}