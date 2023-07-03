package com.haedal.service;

import com.haedal.entity.Pass;
import com.haedal.entity.PassDto;
import com.haedal.entity.User;
import com.haedal.repository.PassRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PassService {

    private final PassRepository passRepository;

    public Pass create(Pass pass) {
        //TOdo : role 검사
        //Todo : 시간 설정 자동화? (Config)
        pass.setStartedDay(
                LocalDateTime.of(
                        pass.getStartedDay().getYear(),
                        pass.getStartedDay().getMonth(),
                        pass.getStartedDay().getDayOfMonth(),
                        0,0,0)
        );
        pass.setEndedDay(
                LocalDateTime.of(
                        pass.getEndedDay().getYear(),
                        pass.getEndedDay().getMonth(),
                        pass.getEndedDay().getDayOfMonth(),
                        0,0,0).plusDays(1)
        );

        Pass saved = passRepository.save(pass);
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

        pass.setName(passDto.name());
        pass.setPrice(passDto.price());
        pass.setCount(passDto.count());
        pass.setStartedDay(passDto.startedDay());
        pass.setEndedDay(passDto.endedDay());
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