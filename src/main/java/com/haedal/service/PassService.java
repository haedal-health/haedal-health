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
        return passRepository.findById(passId)
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 Pass입니다 - passId: " + passId));
    }

    public List<PassDto> getAll() {
        //TODO : pageable 추가 -> map(PassDto::from)
        return passRepository.findAll().stream()
                .map(PassDto::from)
                .collect(Collectors.toList());
    }

    @Transactional
    public Pass updatePass(Long passId, PassDto passDto) throws Exception {
        Pass pass = passRepository.findById(passId)
                .orElseThrow(() ->
                        new EntityNotFoundException("존재하지 않는 Pass입니다 - passId: " + passId));

        pass.setName(passDto.getName());
        pass.setPrice(passDto.getPrice());
        pass.setCount(passDto.getCount());
        pass.setStartedDay(passDto.getStartedDay());
        pass.setEndedDay(passDto.getEndedDay());

        return pass;
    }

    public String deletePass(Long passId){
        Pass pass = passRepository.findById(passId)
                .orElseThrow(() ->
                        new EntityNotFoundException("존재하지 않는 Pass입니다 - passId: " + passId));

        passRepository.delete(pass);
        String answer = pass.getName()+"이 삭제되었습니다.";
        return answer;
    }
}