package com.haedal.controller;


import com.haedal.controller.request.PassCreateRequest;
import com.haedal.model.entity.Pass;
import com.haedal.model.PassDto;
import com.haedal.model.entity.User;
import com.haedal.service.PassService;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/pass")
@RequiredArgsConstructor
public class PassController {

    private final PassService passService;

    //Todo : @ResquestBody PassRequest passRequest
    @PostMapping("")
    public PassDto createPass(@RequestBody PassCreateRequest request) throws AuthenticationException {
        PassDto pass = PassDto.of(request.getName(), request.getCount(), request.getPrice(), request.getStartedDay(), request.getEndedDay());
        Pass savedPass = passService.create(pass);
        PassDto result = PassDto.from(savedPass);
        //Todo : return PassRespose result
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
    public PassDto modifyPass(@PathVariable Long passId, @RequestBody PassDto passDto) throws Exception {
        return PassDto.from(passService.updatePass(passId, passDto));
    }
    @DeleteMapping("/{passId}")
    public String deletePass(@PathVariable Long passId) throws Exception {
        return passService.deletePass(passId);
    }
}