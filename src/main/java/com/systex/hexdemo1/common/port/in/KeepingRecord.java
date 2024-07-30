package com.systex.hexdemo1.common.port.in;

import com.systex.hexdemo1.common.domain.Expense;

import java.util.List;

public interface KeepingRecord {
    Expense addRecord(Expense expense);

    Expense updateRecord(Expense expense);

    void deleteRecord(Long id);

    List<Expense> listRecords();

    Expense getRecord(Long id);
}