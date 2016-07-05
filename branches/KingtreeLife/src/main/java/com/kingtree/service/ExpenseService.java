package com.kingtree.service;

import java.sql.Timestamp;
import java.util.List;

import com.kingtree.model.Expense;

public interface ExpenseService {

	Expense get(int id);

	int add(Expense expense);

	List<Expense> gets(String expenseName, int type, Timestamp start, Timestamp end, int page, int pageSize);

}
