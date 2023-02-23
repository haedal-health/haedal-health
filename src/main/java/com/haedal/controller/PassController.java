package com.haedal.controller;


import com.haedal.entity.Pass;
import com.haedal.entity.PassDto;
import com.haedal.service.PassService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/pass")
@RequiredArgsConstructor
public class PassController {

    private final PassService passService;

    @PostMapping("")
    public PassDto createPass(@RequestBody PassDto passDto){
        Pass pass = passDto.toEntity();
        Pass savedPass = passService.create(pass);
        PassDto result = PassDto.from(savedPass);
        return result;
    }
    @GetMapping("/{passId}")
    public PassDto detailPass(@PathVariable Long passId){
        PassDto pass = PassDto.from(passService.getPass(passId));
        return pass;
    }

}