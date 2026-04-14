package com.brinks.grupo7.ms_email_service_async.core.application.port.output;

import java.util.List;

public interface CustomerProvider {
    List<String> buscarEmailsPorSegmento(String segmento);
}
