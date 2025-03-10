package br.com.fiap.techchallenge01.pagamento.adapter.in.controller.api;

import br.com.fiap.techchallenge01.pagamento.domain.dto.request.PagamentoPedidoRequestDTO;
import br.com.fiap.techchallenge01.pagamento.domain.dto.request.WebhookNotificationDTO;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.preference.Preference;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@Tag(name = "${tag.swagger.pagamento.name}", description = "${tag.swagger.pagamento.description}")
public interface PagamentoApi {

    Preference criarPreferencia(@RequestBody PagamentoPedidoRequestDTO pedido) throws MPException, MPApiException;

    void webhook(@RequestBody WebhookNotificationDTO notification, @RequestHeader("X-MercadoPago-Signature") String signature);

}