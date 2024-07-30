package com.systex.hexdemo1.repsitory;

import com.systex.hexdemo1.entity.ExpenseEntity;
import org.springframework.data.repository.CrudRepository;



public interface ExpenseCRUDRepository extends CrudRepository<ExpenseEntity, Long> {
}