package com.haedal.controller;


import com.haedal.controller.request.PassCreateRequest;
import com.haedal.model.entity.Pass;
import com.haedal.model.PassDto;
import com.haedal.model.entity.User;
import com.haedal.service.PassService;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping(value = "/pass")
@RequiredArgsConstructor
public class PassController {

    private final PassService passService;

    //Todo : @ResquestBody PassRequest passRequest
    @PostMapping("")
    public Pass createPass(@RequestBody PassCreateRequest request, Authentication authentication) throws AuthenticationException {
        PassDto pass = PassDto.of(request.getName(), request.getCount(), request.getPrice(), request.getStartedDay(), request.getEndedDay());
        Pass savedPass = passService.create(pass,authentication);
        //PassDto result = PassDto.from(savedPass);
        //Todo : return PassRespose result
        return savedPass;
    }
    @GetMapping("/{passId}")
    public PassDto detailPass(@PathVariable Long passId, Authentication authentication)throws AuthenticationException{
        PassDto pass = PassDto.from(passService.getPass(passId,authentication));
        return pass;
    }
    @GetMapping("")
    public List<PassDto> listPass(Authentication authentication) throws AuthenticationException {
        return passService.getAll(authentication);
    }
    @PatchMapping("/{passId}")
    public PassDto modifyPass(@PathVariable Long passId, @RequestBody PassDto passDto,Authentication authentication) throws Exception {
        return PassDto.from(passService.updatePass(passId, passDto,authentication));
    }
    @DeleteMapping("/{passId}")
    public String deletePass(@PathVariable Long passId,Authentication authentication) throws Exception {
        return passService.deletePass(passId,authentication);
    }
}