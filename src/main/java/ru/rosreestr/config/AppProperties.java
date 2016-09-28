package ru.rosreestr.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by KatrinaBosh on 28.09.2016.
 */
@Component
public class AppProperties {

    @Value("${ws.client.isur}")
    private String isur;

    public String getIsur() {
        return isur;
    }

    public void setIsur(String isur) {
        this.isur = isur;
    }
}
