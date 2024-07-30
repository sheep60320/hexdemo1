package com.systex.hexdemo1.common.port.out;

import com.systex.hexdemo1.common.domain.Expense;

import java.util.List;

public interface RecordsReposiotry {
    Expense findRecordById(Long id);

    List<Expense> findAllRecords();

    Expense modifyRecord(Expense expense);

    void deleteRecord(Long id);

    Expense addRecord(Expense expense);
}