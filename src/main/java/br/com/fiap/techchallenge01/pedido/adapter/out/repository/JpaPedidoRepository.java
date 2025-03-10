package br.com.fiap.techchallenge01.pedido.adapter.out.repository;

import br.com.fiap.techchallenge01.pedido.adapter.out.entity.JpaPedidoEntity;
import br.com.fiap.techchallenge01.produto.adapter.out.entity.JpaCategoriaProdutoEntity;
import br.com.fiap.techchallenge01.produto.adapter.out.entity.JpaProdutoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

public interface JpaPedidoRepository extends JpaRepository<JpaPedidoEntity, UUID> {

    @Query("SELECT p.id id, " +
            "p.cliente cliente, " +
            "p.codigo codigo, " +
            "p.codigoPagamento codigoPagamento, " +
            "p.dataAtualizacao dataAtualizacao, " +
            "p.dataCriacao dataCriacao, " +
            "p.observacao observacao, " +
            "p.preco preco, " +
            "p.status status, " +
            "p.produtos produtos " +
            "FROM JpaPedidoEntity p " +
            "join JpaProdutoPedidoEntity produtoped on p.produtos = produtoped.id " +
            "ORDER BY p.status, p.dataCriacao")
//    @Query(nativeQuery = true, value = "SELECT p.id, p.cliente, p.codigo, p.codigoPagamento, p.dataAtualizacao, p.dataCriacao, p.observacao, p.preco, p.status FROM JpaPedidoEntity p ORDER BY p.status, p.dataCriacao")
    List<JpaPedidoEntity> listarPorPrioridade();
}