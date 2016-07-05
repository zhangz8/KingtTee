package com.kingtree.service.imp;

import java.sql.Timestamp;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.kingtree.dao.ExpenseMapper;
import com.kingtree.model.Expense;
import com.kingtree.service.ExpenseService;

@Service
public class ExpenseServiceImp implements ExpenseService {

	@Resource
	private ExpenseMapper expenseMapper;

	@Override
	public Expense get(int id) {
		return expenseMapper.selectByPrimaryKey(id);
	}

	@Override
	public int add(Expense expense) {
		return expenseMapper.insert(expense);
	}

	@Override
	public List<Expense> gets(String expenseName, int type, Timestamp start, Timestamp end, int page, int pageSize) {
		return expenseMapper.selectAll(expenseName, type, start, end, page, pageSize);
	}

}
