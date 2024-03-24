package learn.apicruduser;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import learn.apicruduser.exceptions.UserExistException;
import learn.apicruduser.exceptions.UserNotFoundException;


@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(MethodArgumentNotValidException.class)
  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
    ex.getBindingResult().getAllErrors().forEach((error) -> {
        String fieldName = ((FieldError) error).getField();
        String errorMessage = error.getDefaultMessage();
        errors.put(fieldName, errorMessage);
    });
		return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
	}

  @ExceptionHandler(UserExistException.class)
  protected ResponseEntity<Object> handleUserExistError(UserExistException ex) {
        var body = new HashMap<>();
        body.put("message", ex.getMessage());
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
	}

  @ExceptionHandler({UserNotFoundException.class, MethodArgumentTypeMismatchException.class})
  protected ResponseEntity<Object> handleNotFoundError(RuntimeException ex) {
        var body = new HashMap<>();
        if (ex instanceof MethodArgumentTypeMismatchException) {
          body.put("message", "The user with given id not found");
        } else {
          body.put("message", ex.getMessage());
        }
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
	}

  @ExceptionHandler(Exception.class)
  protected ResponseEntity<Object> handleInternalServerError(Exception ex) {
        System.out.println(ex);
        var body = new HashMap<>();
        body.put("message", "Internal server error");
        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}