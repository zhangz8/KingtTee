package com.kingtree.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class Expense implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7030226225935306193L;

	private int id;

	private int dictionaryId;

	private Timestamp expenseTime;

	private String itemName;

	private Double money;

	private String remark;

	private Timestamp gmtCreate;

	private Timestamp gmtModified;

	private int isDelete;

	public Expense() {
	}

	public Expense(Integer dictionaryId, Timestamp expenseTime, String itemName, Double money) {
		this.dictionaryId = dictionaryId;
		this.expenseTime = expenseTime;
		this.itemName = itemName;
		this.money = money;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getItemName() {
		return itemName;
	}

	public Integer getDictionaryId() {
		return dictionaryId;
	}

	public void setDictionaryId(Integer dictionaryId) {
		this.dictionaryId = dictionaryId;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName == null ? null : itemName.trim();
	}

	public Double getMoney() {
		return money;
	}

	public void setMoney(Double money) {
		this.money = money;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark == null ? null : remark.trim();
	}

	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

	public Timestamp getExpenseTime() {
		return expenseTime;
	}

	public void setExpenseTime(Timestamp expenseTime) {
		this.expenseTime = expenseTime;
	}

	public Timestamp getGmtCreate() {
		return gmtCreate;
	}

	public void setGmtCreate(Timestamp gmtCreate) {
		this.gmtCreate = gmtCreate;
	}

	public Timestamp getGmtModified() {
		return gmtModified;
	}

	public void setGmtModified(Timestamp gmtModified) {
		this.gmtModified = gmtModified;
	}

}