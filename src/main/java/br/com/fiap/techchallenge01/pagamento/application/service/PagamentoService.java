package br.com.fiap.techchallenge01.pagamento.application.service;

import br.com.fiap.techchallenge01.pagamento.domain.dto.request.WebhookNotificationDTO;
import br.com.fiap.techchallenge01.pagamento.application.exception.PagamentoNaoEncontradoException;
import br.com.fiap.techchallenge01.pagamento.application.usecase.PagamentoUseCase;
import br.com.fiap.techchallenge01.pagamento.domain.Pagamento;
import br.com.fiap.techchallenge01.pagamento.domain.dto.request.PagamentoPedidoRequestDTO;
import br.com.fiap.techchallenge01.pagamento.domain.repository.PagamentoRepository;
import br.com.fiap.techchallenge01.pagamento.mapper.PagamentoMapper;
import br.com.fiap.techchallenge01.pagamento.domain.dto.response.PagamentoResponseDTO;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.payment.Payment;
import com.mercadopago.resources.preference.Preference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PagamentoService implements PagamentoUseCase {

    private static final Logger logger = LoggerFactory.getLogger(PagamentoService.class);


    @Autowired
    private PagamentoRepository pagamentoRepository;

    @Autowired
    private PagamentoMapper pagamentoMapper;

    @Value("${mercadopago.access.token}")
    private String accessToken;

    @Override
    public Pagamento enviarPagamento(Pagamento pagamento) {
        return pagamentoRepository.enviarPagamento(pagamento);
    }

    @Override
    @Transactional(readOnly = true)
    public PagamentoResponseDTO verificarPagamentoPedido(String idPedido) {
        Pagamento pagamento = pagamentoRepository.buscarPagamentoPorPedidoId(idPedido).orElseThrow(() -> new PagamentoNaoEncontradoException(idPedido));

        return pagamentoMapper.toResponse(pagamento);
    }

    @Override
    public Preference criarPagamento(PagamentoPedidoRequestDTO pedido) {
        return null;
    }

//    public String criarPagamento(PagamentoRequest request) throws MPException {
//        MercadoPago.SDK.setAccessToken(accessToken);
//
//        Preference preference = new Preference();
//
//        Item item = new Item();
//        item.setTitle(request.getDescricao())
//                .setQuantity(1)
//                .setUnitPrice(request.getValor());
//
//        Payer payer = new Payer();
//        payer.setEmail(request.getEmail());
//
//        preference.setPayer(payer);
//        preference.setItems(Collections.singletonList(item));
//        preference.save();
//
//        return preference.getInitPoint();
//    }


//    @Override
//    public Preference criarPreferencia(PagamentoPedido pedido) throws MPException, MPApiException {
//        List<PreferenceItem> items = new ArrayList<>();
//        for (PagamentoItemPedido item : pedido.getItens()) {
//
//            PreferenceItem preferenceItem = PreferenceItem
//                    .builder()
//                    .preferenceItem.setTitle(item.getTitulo());
//                    .preferenceItem.setQuantity(item.getQuantidade());
//                    .preferenceItem.setUnitPrice(item.getPrecoUnitario());
//                    .preferenceItem.setBuild();;
//
//            items.add(preferenceItem);
//        }
//
//        PreferenceRequest preferenceRequest = PreferenceRequest.builder()
//                .items(items)
//                .build();
//
//        PreferenceClient client = new PreferenceClient();
//        return client.create(preferenceRequest);
//    }

    // Metodo para processar a notificação recebida
    @Override
    public void processarNotificacao(WebhookNotificationDTO notificacao) {

        // TODO: ALTERAR O STATUS DO PEDIDO PARA APROVADO OU REJEITADO
        //  VER OUTROS STATUS DE PAGAMENTO PRA CONSIDERAR 3 TENTATIVAS POR EXEMPLO
        try {
            // O Mercado Pago envia um ID de pagamento que você pode usar para consultar a transação.
            // Vamos simular que o ID da transação está na notificação como exemplo.
            String paymentId = extractPaymentIdFromNotification(notificacao);

            // Consultar o status do pagamento
//            Payment payment = Payment.findById(paymentId);
            Payment payment = new Payment();
            // Processar o pagamento dependendo do status
            if (payment.getStatus().equals("approved")) {
                logger.info("Pagamento aprovado: " + payment.getId());
                // Realizar ação como atualizar status no banco de dados, enviar e-mail, etc.
            } else if (payment.getStatus().equals("rejected")) {
                logger.warn("Pagamento rejeitado: " + payment.getId());
                // Realizar ação de erro ou alerta
            } else {
                logger.info("Pagamento com status: " + payment.getStatus());
            }
        } catch (Exception e) {
            logger.error("Erro ao processar a notificação: " + e.getMessage());
        }
    }

    // Metodo para extrair o ID do pagamento da notificação recebida
    private String extractPaymentIdFromNotification(WebhookNotificationDTO notification) {
        // Aqui você deve fazer o parse do JSON e extrair o ID do pagamento
        // Isso pode variar dependendo da estrutura exata do webhook
        return notification.getData().getId(); // Retornar apenas o ID como exemplo
    }

}