package com.kakaowebtoon.outbox.application.port.outgoing;

import com.kakaowebtoon.outbox.infrastructure.adapter.OutBoxEntity;

public interface SaveOutBoxPort {
    void save(OutBoxEntity outBoxEntity);
}
