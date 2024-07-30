package com.systex.hexdemo1.common.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Expense {
    private Long id;
    private String name;
    private int amount;
    private Date createdAt;

}