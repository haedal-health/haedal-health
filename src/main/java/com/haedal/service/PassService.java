package com.haedal.service;

import com.haedal.model.UserRole;
import com.haedal.model.entity.Pass;
import com.haedal.model.PassDto;
import com.haedal.model.entity.User;
import com.haedal.repository.PassRepository;
import com.haedal.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PassService {
    private final PassRepository passRepository;
    private final UserRepository userRepository;
    private final TokenParsingService tokenParsingService;
    public Pass create(PassDto pass, Authentication authentication) throws AuthenticationException {

        User user = userRepository.findByName(tokenParsingService.getEmail(authentication)).orElseThrow();


        if(!user.getRole().equals(UserRole.ADMIN)) {
            throw new AuthenticationException("권한이 없습니다");
        }

        Pass saved = passRepository.save(
                pass.toEntity(pass.getName(),pass.getCount(),pass.getPrice(),pass.getStartedDay(),pass.getEndedDay()));
        return saved;
    }

    public Pass getPass(Long passId, Authentication authentication) throws AuthenticationException {

        User user = userRepository.findByName(tokenParsingService.getEmail(authentication)).orElseThrow();

        if(!user.getRole().equals(UserRole.ADMIN)) {
            throw new AuthenticationException("권한이 없습니다");
        }

        return passRepository.findById(passId)
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 Pass입니다 - passId: " + passId));
    }

    public List<PassDto> getAll(Authentication authentication)throws AuthenticationException {

        User user = userRepository.findByName(tokenParsingService.getEmail(authentication)).orElseThrow();

        if(!user.getRole().equals(UserRole.ADMIN)) {
            throw new AuthenticationException("권한이 없습니다");
        }

        return passRepository.findAll().stream()
                .map(PassDto::from)
                .collect(Collectors.toList());
    }

    @Transactional
    public Pass updatePass(Long passId, PassDto passDto, Authentication authentication) throws Exception {

        User user = userRepository.findByName(tokenParsingService.getEmail(authentication)).orElseThrow();

        if(!user.getRole().equals(UserRole.ADMIN)) {
            throw new AuthenticationException("권한이 없습니다");
        }

        Pass pass = passRepository.findById(passId)
                .orElseThrow(() ->
                        new EntityNotFoundException("존재하지 않는 Pass입니다 - passId: " + passId));

        pass.setName(passDto.getName());
        pass.setPrice(passDto.getPrice());
        pass.setCount(passDto.getCount());
        pass.setStartedDay(passDto.getStartedDay());
        pass.setEndedDay(passDto.getEndedDay());
        passRepository.flush();
        return pass;
    }


    public String deletePass(Long passId, Authentication authentication)throws Exception{

        User user = userRepository.findByName(tokenParsingService.getEmail(authentication)).orElseThrow();

        if(!user.getRole().equals(UserRole.ADMIN)) {
            throw new AuthenticationException("권한이 없습니다");
        }

        Pass pass = passRepository.findById(passId)
                .orElseThrow(() ->
                        new EntityNotFoundException("존재하지 않는 Pass입니다 - passId: " + passId));

        passRepository.delete(pass);
        String answer = pass.getName()+"이 삭제되었습니다.";
        return answer;
    }
}
