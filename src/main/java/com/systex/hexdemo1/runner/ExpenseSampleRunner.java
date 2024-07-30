package com.systex.hexdemo1.runner;

import com.systex.hexdemo1.entity.ExpenseEntity;
import com.systex.hexdemo1.repsitory.ExpenseCRUDRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ExpenseSampleRunner implements CommandLineRunner {
    private static final Logger LOG = LoggerFactory.getLogger(ExpenseSampleRunner.class);
    @Autowired
    private ExpenseCRUDRepository repository;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("print something here");
        log.info("print something here, too.");
        LOG.info("PRINT SOMETHING HERE, TOO2");
        ExpenseEntity e1 = new ExpenseEntity();
        e1.setName("Mark");
        e1.setAmount(500);
        ExpenseEntity e2 = new ExpenseEntity();
        e2.setName("Abby");
        e2.setAmount(400);
        repository.save(e1);
        repository.save(e2);

    }
}