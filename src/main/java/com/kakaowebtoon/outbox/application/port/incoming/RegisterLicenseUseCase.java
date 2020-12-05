package com.kakaowebtoon.outbox.application.port.incoming;

import com.kakaowebtoon.outbox.domain.model.License;

public interface RegisterLicenseUseCase {
    void registerLicense(License license);
}
