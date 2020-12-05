package com.kakaowebtoon.outbox.ui.controller;

import com.kakaowebtoon.outbox.application.port.incoming.RegisterLicenseUseCase;
import com.kakaowebtoon.outbox.domain.model.License;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@RestController
public class LicenseController {

    private final RegisterLicenseUseCase registerLicenseUseCase;

    @PostMapping("/register/license")
    public boolean registerLicence(@RequestParam String userid, @RequestParam Long episodeId) {
        registerLicenseUseCase.registerLicense(License.builder()
                .userid(userid)
                .episodeId(episodeId)
                .expireAtDatetime(LocalDateTime.now()).build());
        return true;
    }
}
