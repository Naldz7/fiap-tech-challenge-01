package br.com.fiap.techchallenge01.pagamento.domain.dto.request;

import lombok.Data;

@Data
public class WebhookNotificationDTO {
    private String type;
    private String action;
    private WebhookDataDTO data;
}