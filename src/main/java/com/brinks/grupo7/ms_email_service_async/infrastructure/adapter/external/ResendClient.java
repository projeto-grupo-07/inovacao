package com.brinks.grupo7.ms_email_service_async.infrastructure.adapter.external;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.Map;

@FeignClient(name = "resend-api", url = "${external.api.resend.url}")
public interface ResendClient {

    @PostMapping("/emails")
    void sendEmail(
            @RequestHeader("Authorization") String token,
            @RequestBody Map<String, Object> payload
            );
}
