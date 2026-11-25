package in.om.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import in.om.entity.ExpenseEntity;
import in.om.entity.UserEntity;

public interface  ExpenseRepository extends JpaRepository<ExpenseEntity,Long>{

	 List<ExpenseEntity> findByCategory(String category);
	
	 List<ExpenseEntity> findByPaymentMode(String paymentMode);
	
	 List<ExpenseEntity> findByCategoryAndPaymentMode(String category, String paymentMode);
	 
	 List<ExpenseEntity> findByUserId(long id);
	 
	 List<ExpenseEntity> findByUserIdAndCategory(long userId,String category);
	 
	 List<ExpenseEntity> findByUserIdAndPaymentMode(long userId,String paymentMode);
	 
	 List<ExpenseEntity> findByUserIdAndCategoryAndPaymentMode(long userId,String category,String paymentMode);
	 
	 Optional<ExpenseEntity> findByIdAndUserId(long id,long userId);
	 
	 List<ExpenseEntity> findByUser(UserEntity user);
}
