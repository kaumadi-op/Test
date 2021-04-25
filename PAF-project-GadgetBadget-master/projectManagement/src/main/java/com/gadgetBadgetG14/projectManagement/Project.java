package com.gadgetBadgetG14.projectManagement;

public class Project {
	
	public int p_id;
	public String p_name;
	public String status;
	public String startDate;
	public String endDate;
	public String description;
	public float budget;
	public float price;
	public int researcher_id;
	public int sponsor_id;
	public int getP_id() {
		return p_id;
	}
	public void setP_id(int p_id) {
		this.p_id = p_id;
	}
	public String getP_name() {
		return p_name;
	}
	public void setP_name(String p_name) {
		this.p_name = p_name;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public float getBudget() {
		return budget;
	}
	public void setBudget(float budget) {
		this.budget = budget;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public int getResearcher_id() {
		return researcher_id;
	}
	public void setResearcher_id(int researcher_id) {
		this.researcher_id = researcher_id;
	}
	public int getSponsor_id() {
		return sponsor_id;
	}
	public void setSponsor_id(int sponsor_id) {
		this.sponsor_id = sponsor_id;
	}
	
	
	@Override
	public String toString() {
		return "Project [p_id=" + p_id + ", p_name=" + p_name + ", status=" + status + ", startDate=" + startDate
				+ ", endDate=" + endDate + ", description=" + description + ", budget=" + budget + ", price=" + price
				+ ", researcher_id=" + researcher_id + ", sponsor_id=" + sponsor_id + "]";
	}
	
}
