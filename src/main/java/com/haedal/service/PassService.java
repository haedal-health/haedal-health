package com.haedal.service;

import com.haedal.entity.Pass;
import com.haedal.repository.PassRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PassService {

    private final PassRepository passRepository;

    public Pass create(Pass pass) {

        Pass saved = passRepository.save(pass);
        return saved;
    }
}
