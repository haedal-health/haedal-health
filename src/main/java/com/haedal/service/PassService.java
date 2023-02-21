package com.haedal.service;

import com.haedal.entity.Pass;
import org.springframework.stereotype.Service;

@Service
public class PassService {
    public String save(Pass pass) {
        return "OK";
    }
}
