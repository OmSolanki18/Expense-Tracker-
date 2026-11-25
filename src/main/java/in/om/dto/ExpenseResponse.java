package in.om.dto;

import java.time.LocalDate;

public class ExpenseResponse {

	private long id;
	private double amount;
	private String description;
	private String category;
	private LocalDate localDate;
	private String paymentMode;

	public ExpenseResponse() {

	}

	public ExpenseResponse(long id, double amount, String description, String category, LocalDate localDate,
			String paymentMode) {
		this.id = id;
		this.amount = amount;
		this.description = description;
		this.category = category;
		this.localDate = localDate;
		this.paymentMode = paymentMode;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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
