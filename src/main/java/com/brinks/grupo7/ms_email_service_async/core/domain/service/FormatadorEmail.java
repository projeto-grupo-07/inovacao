package com.brinks.grupo7.ms_email_service_async.core.domain.service;

import java.util.Map;

public class FormatadorEmail {
    public String prepararCorpoFinal(String textoDoUsuario) {
        if (textoDoUsuario == null) return "";

        String textoFormatado = textoDoUsuario.replace("\n", "<br>");

        return """
            <html>
                <body style="font-family: sans-serif; color: #333;">
                    <div style="max-width: 600px; margin: 0 auto;">
                        <header><h2>Brink Calçados</h2></header>
                        <main>
                            %s
                        </main>
                        <footer style="margin-top: 20px; font-size: 12px; color: #999;">
                            Você recebeu este e-mail da Brink Calçados.
                        </footer>
                    </div>
                </body>
            </html>
            """.formatted(textoFormatado);
    }
}
