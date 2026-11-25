package in.om.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * 
 * 
 */
@Entity
@Table(name = "expenses")
public class ExpenseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private double amount;
	private String description;
	private String category;
	private LocalDate localDate;
	private String paymentMode;

	public ExpenseEntity() {

	}

	public ExpenseEntity(double amount, String description, String category, LocalDate localDate, String paymentMode) {

		this.amount = amount;
		this.description = description;
		this.category = category;
		this.localDate = localDate;
		this.paymentMode = paymentMode;
	}

	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private UserEntity user;

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public void setLocalDate(LocalDate localDate) {
		this.localDate = localDate;
	}

	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}

	public long getId() {
		return id;
	}

	public double getAmount() {
		return amount;
	}

	public String getDescription() {
		return description;
	}

	public String getCategory() {
		return category;
	}

	public LocalDate getLocalDate() {
		return localDate;
	}

	public String getPaymentMode() {
		return paymentMode;
	}

	@Override
	public String toString() {
		return "ExpenseEntity [id=" + id + ", amount=" + amount + ", description=" + description + ", category="
				+ category + ", localDate=" + localDate + ", paymentMode=" + paymentMode + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}

}
