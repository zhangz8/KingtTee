package com.kingtree.dao;

import java.sql.Timestamp;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.kingtree.model.Expense;

public interface ExpenseMapper {
	int deleteByPrimaryKey(int id);

	int insert(Expense record);

	int insertSelective(Expense record);

	Expense selectByPrimaryKey(int id);

	int updateByPrimaryKeySelective(Expense record);

	int updateByPrimaryKey(Expense record);

	List<Expense> selectAll(@Param(value = "expenseName") String expenseName, @Param(value = "type") int type,
			@Param(value = "start") Timestamp start, @Param(value = "end") Timestamp end,
			@Param(value = "page") int page, @Param(value = "pageSize") int pageSize);

	int countAll(@Param(value = "expenseName") String expenseName, @Param(value = "type") int type,
			@Param(value = "start") Timestamp start, @Param(value = "end") Timestamp end);
}