package com.haedal.controller;


import com.haedal.entity.Pass;
import com.haedal.entity.PassDto;
import com.haedal.service.PassService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @GetMapping("")
    public List<PassDto> listPass(){
        return passService.getAll();
    }
    @PatchMapping("/{passId}")
    public PassDto modifyPass(@PathVariable Long passId, PassDto passDto) throws Exception {
        return PassDto.from(passService.updatePass(passId, passDto));
    }
    @DeleteMapping("/{passId}")
    public String deletePass(@PathVariable Long passId) throws Exception {
        return passService.deletePass(passId);
    }
}