package in.om.dto;

public class ExpenseStatisticsResponse {

	private double totalExpense;
	private long expenseCount;
	private double averageExpense;
	private double highestExpense;
	private double lowestExpense;

	public ExpenseStatisticsResponse() {

	}

	public ExpenseStatisticsResponse(double totalExpense, long expenseCount, double averageExpense, double highestExpense ,double lowestExpense) {
		this.totalExpense=totalExpense;
		this.averageExpense=averageExpense;
		this.highestExpense=highestExpense;
		this.lowestExpense=lowestExpense;
	}

	public double getTotalExpense() {
		return totalExpense;
	}

	public void setTotalExpense(double totalExpense) {
		this.totalExpense = totalExpense;
	}

	public long getExpenseCount() {
		return expenseCount;
	}

	public void setExpenseCount(long expenseCount) {
		this.expenseCount = expenseCount;
	}

	public double getAverageExpense() {
		return averageExpense;
	}

	public void setAverageExpense(double averageExpense) {
		this.averageExpense = averageExpense;
	}

	public double getHighestExpense() {
		return highestExpense;
	}

	public void setHighestExpense(double highestExpense) {
		this.highestExpense = highestExpense;
	}

	public double getLowestExpense() {
		return lowestExpense;
	}

	public void setLowestExpense(double lowestExpense) {
		this.lowestExpense = lowestExpense;
	}

}
