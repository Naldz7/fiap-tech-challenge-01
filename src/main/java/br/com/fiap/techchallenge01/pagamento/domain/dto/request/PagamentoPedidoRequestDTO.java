package br.com.fiap.techchallenge01.pagamento.domain.dto.request;

import java.math.BigDecimal;
    import java.util.List;
    import lombok.Data;

    @Data
    public class PagamentoPedidoRequestDTO {

        private Long id;
        private List<PagamentoItemPedidoRequestDTO> itens;
        private BigDecimal valorTotal;
        // Outros atributos do pedido
    }