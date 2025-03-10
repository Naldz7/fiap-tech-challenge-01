package br.com.fiap.techchallenge01.pagamento.application.usecase;

import br.com.fiap.techchallenge01.pagamento.domain.dto.request.WebhookNotificationDTO;
import br.com.fiap.techchallenge01.pagamento.domain.Pagamento;
import br.com.fiap.techchallenge01.pagamento.domain.dto.request.PagamentoPedidoRequestDTO;
import br.com.fiap.techchallenge01.pagamento.domain.dto.response.PagamentoResponseDTO;
import com.mercadopago.resources.preference.Preference;

public interface PagamentoUseCase {

    Pagamento enviarPagamento(Pagamento pagamento);

    PagamentoResponseDTO verificarPagamentoPedido(String idPedido);

    Preference criarPagamento(PagamentoPedidoRequestDTO pedido);

    void processarNotificacao(WebhookNotificationDTO notificacao);
}