package com.kakaowebtoon.outbox.infrastructure.repository;

import com.kakaowebtoon.outbox.infrastructure.adapter.OutBoxEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OutBoxRepository extends JpaRepository<OutBoxEntity, Long> {

}
