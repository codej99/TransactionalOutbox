package com.kakaowebtoon.outbox.infrastructure.adapter;

import com.kakaowebtoon.outbox.application.port.outgoing.RegisterLicensePort;
import com.kakaowebtoon.outbox.infrastructure.repository.LicenseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class RegisterLicenseAdapter implements RegisterLicensePort {

    private final LicenseRepository licenseRepository;

    @Override
    public void register(LicenseEntity licenseEntity) {
        licenseRepository.save(licenseEntity);
    }
}
