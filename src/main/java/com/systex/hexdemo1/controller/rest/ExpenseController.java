package com.systex.hexdemo1.controller.rest;

import com.systex.hexdemo1.common.domain.Expense;
import com.systex.hexdemo1.common.port.in.KeepingRecord;
import com.systex.hexdemo1.controller.form.ExpenseForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Slf4j
public class ExpenseController {
    private static final String LIST_ALL_EXPENSES = "expenses";
    private static final String ADD_MODIFY_EXPENSE_FORM = "expenseForm";
    @Autowired
    private KeepingRecord keepingRecord;

    @GetMapping("/records/all")
    public String listAll(Model model) {
        model.addAttribute(LIST_ALL_EXPENSES, keepingRecord.listRecords());
        return "records/list";
    }

    @GetMapping("/records/delete")
    public String delete(@RequestParam Long id) {
        keepingRecord.deleteRecord(id);
        return "redirect:/records/all";
    }

    @GetMapping("/records/add")
    public String showAdd(Model model) {
        model.addAttribute(ADD_MODIFY_EXPENSE_FORM, new ExpenseForm());
        return "records/add";
    }

    @PostMapping("/records/add")
    public String storeAdd(ExpenseForm f) {
        Expense expense = new Expense();
        BeanUtils.copyProperties(f, expense);
        keepingRecord.addRecord(expense);
        return "redirect:/records/all";
    }

    @GetMapping("/records/modify")
    public String showModify(@RequestParam Long id, Model model) {
        Expense expense = keepingRecord.getRecord(id);
        ExpenseForm f = new ExpenseForm();
        BeanUtils.copyProperties(expense, f);
        model.addAttribute(ADD_MODIFY_EXPENSE_FORM, f);
        return "records/modify";
    }

    @PostMapping("/records/modify")
    public String storeModify(ExpenseForm f) {
        Expense e = keepingRecord.getRecord(f.getId());
        BeanUtils.copyProperties(f, e);
        keepingRecord.updateRecord(e);
        return "redirect:/records/all";
    }
}