package com.haedal.controller;


import com.haedal.entity.Pass;
import com.haedal.service.PassService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/pass")
@RequiredArgsConstructor
public class PassController {

    private final PassService passService;

    //public PassController(){}

    @PostMapping("")
    public Pass createPass(@RequestBody Pass pass){
        return passService.create(pass);
    }
}
