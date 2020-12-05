package com.kakaowebtoon.outbox.infrastructure.adapter;

import com.kakaowebtoon.outbox.application.port.outgoing.SaveOutBoxPort;
import com.kakaowebtoon.outbox.infrastructure.repository.OutBoxRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class SaveOutBoxAdapter implements SaveOutBoxPort {

    private final OutBoxRepository outBoxRepository;

    @Override
    public void save(OutBoxEntity outBoxEntity) {
        outBoxRepository.save(outBoxEntity);
    }
}
