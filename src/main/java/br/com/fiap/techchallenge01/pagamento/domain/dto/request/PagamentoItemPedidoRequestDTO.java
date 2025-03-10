package br.com.fiap.techchallenge01.pagamento.domain.dto.request;

import java.math.BigDecimal;
    import lombok.Data;

    @Data
    public class PagamentoItemPedidoRequestDTO {

        private String titulo;
        private Integer quantidade;
        private BigDecimal precoUnitario;
        // Outros atributos do item
    }