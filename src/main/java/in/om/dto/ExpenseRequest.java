package in.om.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

/**
 * 
 */
public class ExpenseRequest {

	@NotNull(message = "Amount is required")
	@Positive(message = "Amount must greater than 0")
	private double amount;

	@NotBlank(message = "description is required")
	private String description;

	@NotBlank(message = "category is required")
	private String category;

	@NotNull(message = "Date is required")
	private LocalDate localDate;

	@NotBlank(message = "payment mode is required")
	private String paymentMode;


	public ExpenseRequest() {

	}

	public ExpenseRequest(double amount, String description, String category, LocalDate localDate, String paymentMode) {
		this.amount = amount;
		this.description = description;
		this.category = category;
		this.localDate = localDate;
		this.paymentMode = paymentMode;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public LocalDate getLocalDate() {
		return localDate;
	}

	public void setLocalDate(LocalDate localDate) {
		this.localDate = localDate;
	}

	public String getPaymentMode() {
		return paymentMode;
	}

	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}

}
