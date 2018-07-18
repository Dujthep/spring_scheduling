package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class ConfigReader {

    public List<String> list() {
        return Arrays.asList("*/15 * * * * *", "*/12 * * * * *");
    }

}
