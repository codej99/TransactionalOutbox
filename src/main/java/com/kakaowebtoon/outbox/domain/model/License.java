package com.kakaowebtoon.outbox.domain.model;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class License {
    private Long id;
    private String userid;
    private Long episodeId;
    private LocalDateTime expireAtDatetime;
}
