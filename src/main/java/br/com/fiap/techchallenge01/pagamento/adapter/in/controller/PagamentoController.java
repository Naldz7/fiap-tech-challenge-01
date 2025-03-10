package br.com.fiap.techchallenge01.pagamento.adapter.in.controller;

import br.com.fiap.techchallenge01.pagamento.adapter.in.controller.api.PagamentoApi;
import br.com.fiap.techchallenge01.pagamento.application.usecase.PagamentoUseCase;
import br.com.fiap.techchallenge01.pagamento.domain.dto.request.PagamentoPedidoRequestDTO;
import br.com.fiap.techchallenge01.pagamento.domain.dto.request.WebhookNotificationDTO;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.preference.Preference;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pagamentos")
@RequiredArgsConstructor
public class PagamentoController implements PagamentoApi {

    private final PagamentoUseCase pagamentoUseCase;

    @Override
    @PostMapping("/criar-preferencia")
    public Preference criarPreferencia(@RequestBody PagamentoPedidoRequestDTO pedido) throws MPException, MPApiException {
        return pagamentoUseCase.criarPagamento(pedido);
    }

    // Recebe a notificação POST do Mercado Pago
    @Override
    @PostMapping("/webhook")
    public void webhook(@RequestBody WebhookNotificationDTO notification,
                        @RequestHeader("X-MercadoPago-Signature") String signature) {
        // O Mercado Pago envia o "notification" com os detalhes da transação.
        // A string "notification" pode ser um JSON que você precisa parsear
        // para identificar o status do pagamento.
        System.out.println("Recebido Webhook: " + notification);
        if (isSignatureValid(notification, signature)) {
            System.out.println("Pagamento " + notification.getAction() + " para ID: " + notification.getData().getId());

            pagamentoUseCase.processarNotificacao(notification);
        } else {
            System.out.println("Assinatura inválida");
        }
    }

    private boolean isSignatureValid(WebhookNotificationDTO notification, String signature) {
        // Verificar assinatura com o segredo compartilhado
        return true; // Aqui você validaria a assinatura
    }
}