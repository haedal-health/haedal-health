package com.haedal.controller;


import com.haedal.entity.Pass;
import com.haedal.repository.PassRepository;
import com.haedal.service.PassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/pass")
public class PassController {

    @Autowired
    PassRepository passRepository;

    @PostMapping("")
    public Pass postPass(@RequestBody Pass pass){
        Pass newpass = passRepository.save(pass);
        //passService.save(pass);
        return newpass;
    }
}
