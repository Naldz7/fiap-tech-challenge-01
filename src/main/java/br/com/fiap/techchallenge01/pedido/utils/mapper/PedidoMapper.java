package br.com.fiap.techchallenge01.pedido.utils.mapper;

import br.com.fiap.techchallenge01.pedido.domain.Pedido;
import br.com.fiap.techchallenge01.pedido.domain.ProdutoPedido;
import br.com.fiap.techchallenge01.pedido.domain.dto.request.PedidoRequestDTO;
import br.com.fiap.techchallenge01.pedido.domain.dto.request.ProdutoPedidoRequestDTO;
import br.com.fiap.techchallenge01.pedido.domain.dto.response.PedidoResponseDTO;
import br.com.fiap.techchallenge01.produto.application.service.ProdutoService;
import br.com.fiap.techchallenge01.produto.domain.Produto;
import br.com.fiap.techchallenge01.produto.domain.dto.response.ProdutoResponseDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Component
public class PedidoMapper {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ProdutoService produtoService;

    public PedidoResponseDTO toResponse(Pedido pedido) {
        return modelMapper.map(pedido, PedidoResponseDTO.class);
    }

    public Pedido toPedido(PedidoRequestDTO pedidoRequestDTO) {
        Pedido pedido = new Pedido();
        List<ProdutoPedido> produtos = new ArrayList<>();
        ProdutoPedido produtoPedido;
        ProdutoResponseDTO produtoEncontrado;
        var precoTotal = new BigDecimal(BigInteger.ZERO);

        for (ProdutoPedidoRequestDTO produtoPedidoRequestDTO : pedidoRequestDTO.getProdutos()) {
            produtoEncontrado = produtoService.buscarProdutoPorId(produtoPedidoRequestDTO.getIdProduto());
            Produto produto = modelMapper.map(produtoEncontrado, Produto.class);

            precoTotal = precoTotal.add(produto.getPreco().multiply(BigDecimal.valueOf(produtoPedidoRequestDTO.getQuantidade())));

            produtoPedido = new ProdutoPedido();
            produtoPedido.setProduto(produto);
            produtoPedido.setQuantidade(produtoPedidoRequestDTO.getQuantidade());
            produtoPedido.setObservacao(produtoPedidoRequestDTO.getObservacao());
            produtos.add(produtoPedido);
        }
        pedido.setProdutos(produtos);
        pedido.setCodigo(gerarCodigo());
        pedido.setStatus(StatusPedido.ABERTO.toString());
        pedido.setObservacao(pedidoRequestDTO.getObservacao());
        pedido.setPreco(precoTotal);
        return pedido;


//        produtoPedido = ProdutoPedido.builder()
//                .produto(produto)
//                .quantidade(produtoPedidoRequestDTO.getQuantidade())
//                .observacao(produtoPedidoRequestDTO.getObservacao())
//                .build();
//        produtos.add(produtoPedido);
//    }
//
//        return Pedido.builder()
//                .produtos(produtos)
//                .codigo(gerarCodigo())
//            .status(StatusPedido.ABERTO.toString())
//            .observacao(pedidoRequestDTO.getObservacao())
//            .preco(precoTotal)
//                .build();
    }

    public List<PedidoResponseDTO> toCollectionResponse(List<Pedido> pedidos) {
        return pedidos.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    private String gerarCodigo() {
        var CARACTERES = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        var TAMANHO_CODIGO = 5;

        Random random = new Random();
        StringBuilder codigo = new StringBuilder();

        for (int i = 0; i < TAMANHO_CODIGO; i++) {
            int indice = random.nextInt(CARACTERES.length());
            codigo.append(CARACTERES.charAt(indice));
        }
        return codigo.toString();
    }
}