package com.brinks.grupo7.ms_email_service_async.infrastructure.adapter.external;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "brink-main-api", url = "${external.api.customer.url}")
public interface CustomerClient {

    @GetMapping("/api/customers/emails")
    List<String> getEmails(@RequestParam("segment") String segment);
}
