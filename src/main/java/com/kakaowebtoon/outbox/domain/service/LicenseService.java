package com.kakaowebtoon.outbox.domain.service;

import com.kakaowebtoon.outbox.application.port.incoming.RegisterLicenseUseCase;
import com.kakaowebtoon.outbox.application.port.outgoing.RegisterLicensePort;
import com.kakaowebtoon.outbox.common.EventPublisher;
import com.kakaowebtoon.outbox.common.EventUtils;
import com.kakaowebtoon.outbox.domain.model.License;
import com.kakaowebtoon.outbox.infrastructure.adapter.LicenseEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class LicenseService implements RegisterLicenseUseCase {

    private final RegisterLicensePort registerLicensePort;
    private final EventPublisher eventPublisher;

    @Override
    @Transactional
    public void registerLicense(License license) {
        LicenseEntity licenseEntity = LicenseEntity.builder().userid(license.getUserid()).episodeId(license.getEpisodeId()).expireAtDatetime(license.getExpireAtDatetime()).build();
        registerLicensePort.register(licenseEntity);
        eventPublisher.publish(EventUtils.createLicenseEvent(licenseEntity, EventUtils.EventType.INSERT));
    }
}
