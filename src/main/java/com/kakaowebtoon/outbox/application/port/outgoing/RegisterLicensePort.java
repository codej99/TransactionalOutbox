package com.kakaowebtoon.outbox.application.port.outgoing;

import com.kakaowebtoon.outbox.infrastructure.adapter.LicenseEntity;

public interface RegisterLicensePort {
    void register(LicenseEntity licenseEntity);
}
