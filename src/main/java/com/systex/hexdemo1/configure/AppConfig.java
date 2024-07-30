package com.systex.hexdemo1.configure;

import com.systex.hexdemo1.adapter.out.db.RecordsCRUDJPARepository;
import com.systex.hexdemo1.common.port.out.RecordsReposiotry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public RecordsReposiotry getRecordsRepository() {
        // real adapter implementation
        return new RecordsCRUDJPARepository();
    }
}