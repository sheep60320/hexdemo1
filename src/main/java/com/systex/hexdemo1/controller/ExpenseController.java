package com.systex.hexdemo1.controller;

import com.systex.hexdemo1.controller.form.ExpenseForm;
import com.systex.hexdemo1.entity.ExpenseEntity;
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
    private com.systex.hexdemo1.repsitory.ExpenseCRUDRepository repository;

    @GetMapping("/records/all")
    public String listAll(Model model) {
        Iterable<ExpenseEntity> expenses = repository.findAll();
//        List<Expense> expenses = new ArrayList<>();
//        repository.findAll().forEach(expense -> expenses.add(expense));
        model.addAttribute(LIST_ALL_EXPENSES, expenses);
        return "records/list";
    }

    @GetMapping("/records/delete")
    public String delete(@RequestParam Long id) {
        if (!repository.findById(id).isPresent()) {
            throw new com.systex.hexdemo1.exception.IDNotFoundEception(id);
        }
        repository.deleteById(id);
        return "redirect:/records/all";
    }

    @GetMapping("/records/add")
    public String showAdd(Model model) {
        ExpenseForm f = new ExpenseForm();
        model.addAttribute(ADD_MODIFY_EXPENSE_FORM, f);
        return "records/add";
    }

    @PostMapping("/records/add")
    public String storeAdd(ExpenseForm f) {
        log.info("get a form as:{}", f);
        ExpenseEntity e = new ExpenseEntity();
        BeanUtils.copyProperties(f, e);
        repository.save(e);
        return "redirect:/records/all";
    }

    @GetMapping("/records/modify")
    public String showModify(@RequestParam Long id, Model model) {
        if (repository.findById(id).isEmpty()) {
            throw new com.systex.hexdemo1.exception.IDNotFoundEception(id);
        }
        // load data from db
        ExpenseEntity expenseEntity = repository.findById(id).get();
        ExpenseForm f = new ExpenseForm();
        // entity ==> form
        BeanUtils.copyProperties(expenseEntity, f);
        model.addAttribute(ADD_MODIFY_EXPENSE_FORM, f);
        return "records/modify";
    }

    @PostMapping("/records/modify")
    public String storeModify(ExpenseForm f) {
        // form==>entity==>db
        ExpenseEntity expenseEntity = repository.findById(f.getId()).get();
        BeanUtils.copyProperties(f, expenseEntity);
        repository.save(expenseEntity);
        return "redirect:/records/all";
    }


}