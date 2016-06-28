package com.kingtree.service;

import java.sql.Timestamp;
import java.util.List;

import com.kingtree.model.Expense;

public interface ExpenseService {

	Expense get(int id);

	int add(Expense expense);

	List<Expense> gets(Timestamp start, Timestamp end);

}
