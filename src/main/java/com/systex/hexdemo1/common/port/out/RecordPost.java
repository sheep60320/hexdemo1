package com.systex.hexdemo1.common.port.out;

import com.systex.hexdemo1.common.domain.Expense;
import com.systex.hexdemo1.common.message.RecordMessage;
import org.springframework.beans.BeanUtils;

public interface RecordPost {
    public void postAddMessage(Expense expense);

    public void postModifyMessage(Expense expense);

    static RecordMessage getMessageFromBean(Expense expense) {
        RecordMessage m = new RecordMessage();
        BeanUtils.copyProperties(expense, m);
        return m;
    }

}