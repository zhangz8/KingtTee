package com.kingtree.controller;

import java.sql.Timestamp;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kingtree.model.Expense;
import com.kingtree.service.ExpenseService;

@Controller
public class ExpenseController {

	@Resource
	private ExpenseService expenseService;

	@RequestMapping("/list")
	public String list(Model model,
			@RequestParam(required = false, defaultValue = "2015-01-01 00:00:00") Timestamp start,
			@RequestParam(required = false, defaultValue = "2017-01-01 00:00:00") Timestamp end) {
		List<Expense> expenses = expenseService.gets(start, end);
		model.addAttribute("expenses", expenses);
		return "list";
	}
}
