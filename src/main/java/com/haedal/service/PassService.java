package com.haedal.service;

import com.haedal.entity.Pass;
import com.haedal.repository.PassRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.Optional;

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
}