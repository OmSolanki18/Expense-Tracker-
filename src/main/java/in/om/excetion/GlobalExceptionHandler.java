package in.om.excetion;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(ExpenseNotFoundException.class)
	public ResponseEntity<ExceptionResponse> handleExpenseNotFound(ExpenseNotFoundException ex){
		
		ExceptionResponse er=new ExceptionResponse(404,ex.getMessage(),LocalDateTime.now());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(er);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String,String>> handleValidationError(MethodArgumentNotValidException ex){ 
		
		Map<String,String> errors=new HashMap<>();
		 ex.getBindingResult()
         .getFieldErrors()
         .forEach(error ->errors.put(error.getField(),error.getDefaultMessage() ));

		 return ResponseEntity.badRequest().body(errors);
	}
	
	@ExceptionHandler(EmailAlreadyExistsException.class)
	public ResponseEntity<Map<String, String>> handleEmailAlreadyExists(
	        EmailAlreadyExistsException ex) {

	    Map<String, String> error = new HashMap<>();

	    error.put("error", ex.getMessage());

	    return ResponseEntity
	            .status(HttpStatus.CONFLICT)
	            .body(error);
	}
	
	@ExceptionHandler(DeleteExpenseException.class)
	public ResponseEntity<DeleteExpenseException> handleDeleteException(String ex){
		DeleteExpenseException er= new DeleteExpenseException(ex);
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(er);
	}
}
