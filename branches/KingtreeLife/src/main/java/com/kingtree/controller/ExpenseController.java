package com.kingtree.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.kingtree.controller.vo.JsonVO;
import com.kingtree.enums.AnalysisType;
import com.kingtree.model.Dictionary;
import com.kingtree.model.Expense;
import com.kingtree.service.DictionaryService;
import com.kingtree.service.ExpenseService;

@Controller
@RequestMapping("/expense")
public class ExpenseController {

	@Resource
	private ExpenseService expenseService;

	@Resource
	private DictionaryService dictionaryService;

	private static final String EXPENSE_TYPE = "消费类型";

	@RequestMapping("/list")
	public String list(Model model, @RequestParam(required = false) String startStr,
			@RequestParam(required = false) String endStr, @RequestParam(required = false, defaultValue = "0") int type,
			@RequestParam(required = false) String expenseName,
			@RequestParam(required = false, defaultValue = "0") int analysisType,
			@RequestParam(required = false, defaultValue = "0") int page,
			@RequestParam(required = false, defaultValue = "20") int pageSize) {
		Timestamp start = null, end = null;
		try {
			start = Timestamp.valueOf(startStr);
			model.addAttribute("start", start);
		} catch (Exception e) {
		}
		try {
			end = Timestamp.valueOf(endStr);
			model.addAttribute("end", end);
		} catch (Exception e) {
		}

		List<Dictionary> dictionaryList = dictionaryService.gets(EXPENSE_TYPE);
		model.addAttribute("dictionaryList", dictionaryList);
		model.addAttribute("dictionaryJsons", JSON.toJSON(dictionaryList));

		List<Expense> expenses = expenseService.gets(expenseName, type, start, end, page, Integer.MAX_VALUE);
		// if (expenses != null && !expenses.isEmpty()) {
		// Map<Integer, List<Expense>> data = new HashMap<>();
		// for (Expense expense : expenses) {
		// List<Expense> list = data.get(expense.getDictionaryId());
		// if (list != null) {
		// list.add(expense);
		// } else {
		// List<Expense> newList = new ArrayList<>();
		// data.put(expense.getDictionaryId(), newList);
		// }
		// }
		//
		// }
		model.addAttribute("expenses", expenses);
		model.addAttribute("type", type);
		model.addAttribute("expenseName", expenseName);
		model.addAttribute("analysisTypes", AnalysisType.values());
		model.addAttribute("analysisType", analysisType);
		return "/expense/list";
	}

	@RequestMapping("/toAdd")
	public String toAdd(Model model) {
		List<Dictionary> dictionaryList = dictionaryService.gets(EXPENSE_TYPE);
		model.addAttribute("dictionaryList", dictionaryList);
		return "/expense/add";
	}

	@ResponseBody
	@RequestMapping("/add")
	public JsonVO add(@RequestParam String expenseName, @RequestParam(required = false, defaultValue = "0") int type,
			@RequestParam(required = false) String expenseTimeStr,
			@RequestParam(required = false, defaultValue = "0") double expenseMoney, @RequestParam String remark) {
		JsonVO jsonVO = new JsonVO();
		Timestamp expenseTime = null;
		if (StringUtils.isBlank(expenseTimeStr) || StringUtils.isBlank(expenseName) || expenseMoney <= 0 || type <= 0) {
			jsonVO.setMsg("参数不完整");
			return jsonVO;
		}
		try {
			expenseTime = Timestamp.valueOf(expenseTimeStr);
		} catch (Exception e) {
			jsonVO.setMsg("时间格式不正确");
			return jsonVO;
		}
		Expense expense = new Expense(type, expenseTime, expenseName, expenseMoney);
		jsonVO.setIsSuccess(expenseService.add(expense));
		return jsonVO;
	}

}
