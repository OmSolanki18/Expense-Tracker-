package in.om.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import in.om.dto.ExpenseRequest;
import in.om.dto.ExpenseResponse;
import in.om.dto.ExpenseStatisticsResponse;
import in.om.security.SecurityUtil;
import in.om.service.ExpenseService;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("/api/expense")
public class ExpenseController {

	
	@Autowired
	private ExpenseService expenseService;
	
	@PostMapping
	public ExpenseResponse addExpense(@Valid @RequestBody ExpenseRequest request) {
		return expenseService.addExpense(request);
	}
	
	/*@GetMapping
	public List<ExpenseResponse> getAllExpesne(@RequestParam  long id){
		return expenseService.getAllExpense(id);
	}*/
	
	@GetMapping("/{id}")
	public ResponseEntity<ExpenseResponse> getExpenseById(
	        @PathVariable Long id) {

	    long userId = SecurityUtil.getCurrentUser().getUserId();

	    return ResponseEntity.ok(
	            expenseService.getExpenseById(id, userId)
	    );
	}
	
	@PutMapping("/{id}")
	public ExpenseResponse updateById(@PathVariable Long id, @Valid @RequestBody ExpenseRequest expense) {
		long userId = SecurityUtil.getCurrentUser().getUserId();
		return expenseService.updateById(id,userId, expense);
	}
	@DeleteMapping("/{id}")
	public  ResponseEntity<Void> deleteById(@PathVariable long id) {
		long userId = SecurityUtil.getCurrentUser().getUserId();
		expenseService.deleteById(id,userId);
		return ResponseEntity.noContent().build();
	} 
	
	@GetMapping 
	public ResponseEntity<List<ExpenseResponse>> filter(	
			@RequestParam(required=false) String category,
			@RequestParam(required=false) String paymentMode){
		
		long userId =SecurityUtil.getCurrentUser().getUserId();
		return ResponseEntity.ok(expenseService.filter(userId,category,paymentMode));
	}
	
	@GetMapping("/statistics")
	public ResponseEntity<ExpenseStatisticsResponse> getStatistics() {

		long userId =SecurityUtil.getCurrentUser().getUserId();
	    return ResponseEntity.ok(expenseService.getStatistics(userId) );
	}
}
