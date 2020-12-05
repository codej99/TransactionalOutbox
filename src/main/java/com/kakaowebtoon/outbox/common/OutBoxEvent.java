package com.kakaowebtoon.outbox.common;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

@Getter
@Builder
public class OutBoxEvent {
    @NonNull
    private String aggregateId;
    @NonNull
    private EventUtils.AggregateType aggregateType;
    @NonNull
    private EventUtils.EventType eventType;
    @NonNull
    private JsonNode payload;
}
