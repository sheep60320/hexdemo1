package com.systex.hexdemo1.adapter.out.db;

import com.systex.hexdemo1.common.domain.Expense;
import com.systex.hexdemo1.common.port.out.RecordsReposiotry;
import com.systex.hexdemo1.entity.ExpenseEntity;
import com.systex.hexdemo1.exception.IDNotFoundEception;
import com.systex.hexdemo1.repsitory.ExpenseCRUDRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

public class RecordsCRUDJPARepository implements RecordsReposiotry {
    @Autowired
    private ExpenseCRUDRepository repository;

    private void checkIdExist(Long id) {
        if (repository.findById(id).isEmpty()) {
            throw new IDNotFoundEception(id);
        }
    }

    private Expense getBeanFromEntity(ExpenseEntity entity) {
        Expense expense = new Expense();
        BeanUtils.copyProperties(entity, expense);
        // custom logic will be added here
        return expense;
    }

    private ExpenseEntity getEntityFromBean(Expense expense) {
        ExpenseEntity entity = new ExpenseEntity();
        // custom transformation
        BeanUtils.copyProperties(expense, entity);
        return entity;
    }


    @Override
    public Expense findRecordById(Long id) {
        checkIdExist(id);
        ExpenseEntity entity = repository.findById(id).get();
        return getBeanFromEntity(entity);
    }

    @Override
    public List<Expense> findAllRecords() {
        List<Expense> expenses = new ArrayList<>();
        repository.findAll().forEach(e -> {
            expenses.add(getBeanFromEntity(e));
        });
        return expenses;
    }

    @Override
    public Expense modifyRecord(Expense expense) {
        ExpenseEntity entity = repository.findById(expense.getId()).get();
        BeanUtils.copyProperties(expense, entity);
        ExpenseEntity modifiedEntity = repository.save(entity);
        return getBeanFromEntity(modifiedEntity);
    }

    @Override
    public void deleteRecord(Long id) {
        checkIdExist(id);
        repository.deleteById(id);
    }

    @Override
    public Expense addRecord(Expense expense) {
        ExpenseEntity entity = repository.save(getEntityFromBean(expense));
        return getBeanFromEntity(entity);
    }
}