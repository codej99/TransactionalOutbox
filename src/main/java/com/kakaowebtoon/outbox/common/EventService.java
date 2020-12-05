package com.kakaowebtoon.outbox.common;

import com.kakaowebtoon.outbox.application.port.outgoing.SaveOutBoxPort;
import com.kakaowebtoon.outbox.infrastructure.adapter.OutBoxEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@RequiredArgsConstructor
@Service
public class EventService {

    private final SaveOutBoxPort saveOutBoxPort;

    @EventListener
    public void handleOutBoxEvent(OutBoxEvent event) {
        OutBoxEntity outBox = OutBoxEntity.builder()
                .aggregateId(event.getAggregateId())
                .aggregateType(event.getAggregateType().name())
                .eventType(event.getEventType().name())
                .payload(event.getPayload().toString())
                .createdAtDatetime(LocalDateTime.now())
                .build();
        saveOutBoxPort.save(outBox);
    }
}
