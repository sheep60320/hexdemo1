package com.systex.hexdemo1.common.message;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecordMessage {
    private String type;
    private String name;
    private int amount;
}