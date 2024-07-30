package com.systex.hexdemo1.controller.rest;

import com.systex.hexdemo1.common.domain.Expense;
import com.systex.hexdemo1.common.port.in.KeepingRecord;
import com.systex.hexdemo1.controller.form.ExpenseForm;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${apiPrefix}/expenses")
public class ExpenseRestController {
    @Autowired
    private KeepingRecord keepingRecord;

    @GetMapping("/all")
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(keepingRecord.listRecords(), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<?> createExpense(@RequestBody ExpenseForm f) {
        Expense e = new Expense();
        BeanUtils.copyProperties(f, e);
        Expense result = keepingRecord.addRecord(e);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/{expenseId}")
    public ResponseEntity<?> getExpenseById(@PathVariable Long expenseId) {
        Expense record = keepingRecord.getRecord(expenseId);
        return new ResponseEntity<>(record, HttpStatus.OK);
    }

    @DeleteMapping("/{expenseId}")
    public ResponseEntity<?> deleteExpenseById(@PathVariable Long expenseId) {
        keepingRecord.deleteRecord(expenseId);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @PatchMapping("")
    public ResponseEntity<?> updateExpense(@RequestBody ExpenseForm f) {
        Expense e = keepingRecord.getRecord(f.getId());
        BeanUtils.copyProperties(f, e);
        Expense r = keepingRecord.updateRecord(e);
        return new ResponseEntity<>(r, HttpStatus.OK);
    }
}