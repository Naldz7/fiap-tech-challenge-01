package br.com.fiap.techchallenge01.pagamento.domain.dto.request;

import lombok.Data;

@Data
public class PagamentoRequestDTO {
    private String descricao;
    private Float valor;
    private String email;
}