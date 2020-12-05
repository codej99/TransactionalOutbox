package com.kakaowebtoon.outbox.infrastructure.adapter;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Builder
@Entity
@Table(name = "outbox")
public class OutBoxEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 50)
    private String aggregateId;
    @Column(length = 30)
    private String aggregateType;
    @Column(length = 30)
    private String eventType;
    @Column(columnDefinition="TEXT")
    private String payload;
    private LocalDateTime createdAtDatetime;
}
