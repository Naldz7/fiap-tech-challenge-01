package br.com.fiap.techchallenge01.pedido.adapter.out.entity;

import br.com.fiap.techchallenge01.core.utils.entity.JpaBaseEntity;
import br.com.fiap.techchallenge01.produto.adapter.out.entity.JpaProdutoEntity;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "produto_pedido")
public class JpaProdutoPedidoEntity extends JpaBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "quantidade")
    private Long quantidade;

    @Column(name = "observacao")
    private String observacao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_pedido", nullable=false)
    private JpaPedidoEntity pedido;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_produto", nullable=false)
    private JpaProdutoEntity produto;
}