package com.brinks.grupo7.ms_email_service_async.core.application.port.output;

import com.brinks.grupo7.ms_email_service_async.core.domain.model.vo.Email;

public interface MessagePublisher {
    void publishEmailTask(Email email);
}
