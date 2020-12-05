package com.kakaowebtoon.outbox.infrastructure.repository;

import com.kakaowebtoon.outbox.infrastructure.adapter.LicenseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LicenseRepository extends JpaRepository<LicenseEntity, Long> {

}
