package com.systex.hexdemo1.adapter.in;

import com.systex.hexdemo1.common.domain.Expense;
import com.systex.hexdemo1.common.port.in.KeepingRecord;
import com.systex.hexdemo1.common.port.out.RecordsReposiotry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Recorder implements KeepingRecord {
    @Autowired
    private RecordsReposiotry reposiotry;

    @Override
    public Expense addRecord(Expense expense) {
        // maybe add other logic
        return reposiotry.addRecord(expense);
    }

    @Override
    public Expense updateRecord(Expense expense) {
        return reposiotry.modifyRecord(expense);
    }

    @Override
    public void deleteRecord(Long id) {
        reposiotry.deleteRecord(id);
    }

    @Override
    public List<Expense> listRecords() {
        return reposiotry.findAllRecords();
    }

    @Override
    public Expense getRecord(Long id) {
        return reposiotry.findRecordById(id);
    }
}