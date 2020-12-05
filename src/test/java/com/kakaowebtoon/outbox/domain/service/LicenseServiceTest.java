package com.kakaowebtoon.outbox.domain.service;

import com.kakaowebtoon.outbox.domain.model.License;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class LicenseServiceTest {

    @Autowired
    private LicenseService licenseService;

    @Test
    void registerLicenseTest() {
        License license = License.builder()
                .userid("TEST_USER")
                .episodeId(12345L)
                .expireAtDatetime(LocalDateTime.now().plusDays(30)).build();
        licenseService.registerLicense(license);
    }
}