package in.om.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import in.om.dto.ExpenseRequest;
import in.om.dto.ExpenseResponse;
import in.om.dto.ExpenseStatisticsResponse;
import in.om.entity.ExpenseEntity;
import in.om.entity.UserEntity;
import in.om.excetion.DeleteExpenseException;
import in.om.excetion.ExpenseNotFoundException;
import in.om.repository.ExpenseRepository;
import in.om.repository.UserRepository;

@Service
public class ExpenseService<ExpesneEntity> {

	@Autowired
	private ExpenseRepository expenseRepo;

	@Autowired
	private UserRepository userRepo;
	
	//Get All Data 
	/*public List<ExpenseResponse> getAllExpense(long id) {
		List<ExpenseEntity> expense = expenseRepo.findByUserId(id);
		return expense.stream().map(this::mapToResponse).toList();
	}
     */
	//Add Data 
	public ExpenseResponse addExpense(ExpenseRequest request) {
		
		 Authentication authentication =
		            SecurityContextHolder.getContext().getAuthentication();

		    String email = authentication.getName();

		    UserEntity user = userRepo.findByEmail(email)
		            .orElseThrow(() -> new RuntimeException("User not found"));
		    
		ExpenseEntity expense = new ExpenseEntity();
		expense.setAmount(request.getAmount());
		expense.setDescription(request.getDescription());
		expense.setCategory(request.getCategory());
		expense.setLocalDate(request.getLocalDate());
		expense.setPaymentMode(request.getPaymentMode());
		expense.setUser(user);
		ExpenseEntity savedResponse = expenseRepo.save(expense);
		return mapToResponse(savedResponse);
	}
	
	//Map Entity Value To Response DTO
	private ExpenseResponse mapToResponse(ExpenseEntity expense) {
		ExpenseResponse response = new ExpenseResponse();
		response.setId(expense.getId());
		response.setAmount(expense.getAmount());
		response.setDescription(expense.getDescription());
		response.setCategory(expense.getCategory());
		response.setLocalDate(expense.getLocalDate());
		response.setPaymentMode(expense.getPaymentMode());
		return response;
	}

	//Get Value By Id 
	public ExpenseResponse getExpenseById(Long id, Long userId) {

	    ExpenseEntity expense = expenseRepo.findByIdAndUserId(id, userId)
	            .orElseThrow(() ->
	                    new ExpenseNotFoundException("Expense not found"));

	    return mapToResponse(expense);
	}
	//Update Value By Id
	public ExpenseResponse updateById(Long id,Long userId, ExpenseRequest request) {
	    ExpenseEntity expense = expenseRepo.findByIdAndUserId(id,userId).orElseThrow(() -> new ExpenseNotFoundException("Expense not found"));
		expense.setAmount(request.getAmount());
		expense.setDescription(request.getDescription());
		expense.setCategory(request.getCategory());
		expense.setLocalDate(request.getLocalDate());
		expense.setPaymentMode(request.getPaymentMode());
		ExpenseEntity updateExpense = expenseRepo.save(expense);
		return mapToResponse(updateExpense);
	}
	
	//Delete By Id 
	public void deleteById(Long id,Long userId) {
		ExpenseEntity exist = expenseRepo.findByIdAndUserId(id,userId).orElseThrow(()->new DeleteExpenseException("id not avilable"));
		expenseRepo.delete(exist);
	}
	 
	//filter by category and payment 
	public List<ExpenseResponse> filter(long userId, String category, String paymentMode ){
		List<ExpenseEntity> expense ;
		if(category!=null&&paymentMode!=null) {
			expense =expenseRepo.findByUserIdAndCategoryAndPaymentMode(userId,category, paymentMode);
		}
		else if (category!=null) {
			expense =expenseRepo.findByUserIdAndCategory(userId,category);
		}
		else if(paymentMode!=null) {
			expense = expenseRepo.findByUserIdAndPaymentMode(userId,paymentMode);
		}
		else {
			expense = expenseRepo.findByUserId(userId);
		}
		return expense.stream().map(this::mapToResponse).toList();
	}
	
	
	public ExpenseStatisticsResponse getStatistics(long userId) {
	    List<ExpenseEntity> expenses = expenseRepo.findByUserId(userId);
	    if (expenses.isEmpty()) {
	        return new ExpenseStatisticsResponse(
	                0.0,
	                0,
	                0.0,
	                0.0,
	                0.0
	        );
	    }

	    double total = expenses.stream()
	            .mapToDouble(ExpenseEntity::getAmount)
	            .sum();

	    double highest = expenses.stream()
	            .mapToDouble(ExpenseEntity::getAmount)
	            .max()
	            .orElse(0.0);

	    double lowest = expenses.stream()
	            .mapToDouble(ExpenseEntity::getAmount)
	            .min()
	            .orElse(0.0);

	    double average = total / expenses.size();

	    return new ExpenseStatisticsResponse(
	            total,
	            expenses.size(),
	            average,
	            highest, 
	            lowest
	    );
	}
}
