package com.haedal.service;

import com.haedal.model.UserRole;
import com.haedal.model.entity.Pass;
import com.haedal.model.PassDto;
import com.haedal.model.entity.User;
import com.haedal.repository.PassRepository;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PassService {

    private final PassRepository passRepository;

    public Pass create(PassDto pass, User user) throws AuthenticationException {
        //TOdo : role 검사
        if(!user.getRole().equals(UserRole.ADMIN)) {
            //TODO : message Enum 화
            throw new AuthenticationException("권한이 없습니다");
        }
        Pass saved = passRepository.save(pass.toEntity());
        return saved;
    }

    public Pass getPass(Long passId) {
        //TOdo : role 검사

        return passRepository.findById(passId)
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 Pass입니다 - passId: " + passId));
    }

    public List<PassDto> getAll() {
        //TOdo : role 검사
        //TODO : pageable 추가 -> map(PassDto::from)
        return passRepository.findAll().stream()
                .map(PassDto::from)
                .collect(Collectors.toList());
    }

    @Transactional
    public Pass updatePass(Long passId, PassDto passDto) throws Exception {
        //TOdo : role 검사

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
    //Todo: Pass에 이용자 등록하기
    //로그인한 유저가 관리자인가? 검사가 필요
    // 만약 이미 이용중이면 -> 에러 반환
    // 이용하지 않고 있을때만 추가가능

    public String deletePass(Long passId){
        //TOdo : role 검사
        Pass pass = passRepository.findById(passId)
                .orElseThrow(() ->
                        new EntityNotFoundException("존재하지 않는 Pass입니다 - passId: " + passId));

        passRepository.delete(pass);
        String answer = pass.getName()+"이 삭제되었습니다.";
        return answer;
    }
}