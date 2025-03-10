package br.com.fiap.techchallenge01.pedido.adapter.out.entity;

import br.com.fiap.techchallenge01.cliente.adapter.out.entity.JpaClienteEntity;
import br.com.fiap.techchallenge01.cliente.domain.Cliente;
import br.com.fiap.techchallenge01.core.utils.entity.JpaBaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "pedido")
public class JpaPedidoEntity extends JpaBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "codigo")
    private String codigo;

    @Column(name = "status")
    private String status;

    @Column(name = "preco")
    private BigDecimal preco;

    @Column(name = "observacao")
    private String observacao;

    @Column(name = "cd_pagamento")
    private String codigoPagamento;

    @ManyToOne
    @JoinColumn(name="id_cliente", nullable=false)
    private JpaClienteEntity cliente;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "pedido", orphanRemoval = true)
    private List<JpaProdutoPedidoEntity> produtos = new ArrayList<>();
}