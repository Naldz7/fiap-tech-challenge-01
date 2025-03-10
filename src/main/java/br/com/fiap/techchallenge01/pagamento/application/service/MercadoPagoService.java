//package br.com.fiap.techchallenge01.pagamento.application.service;
//
//import com.mercadopago.client.preference.*;
//import com.mercadopago.exceptions.MPApiException;
//import com.mercadopago.exceptions.MPException;
//import com.mercadopago.resources.preference.Preference;
//import org.springframework.stereotype.Service;
//
//@Service
//public class MercadoPagoService {
//
//    public Preference criarPreferencia(Pedido pedido) throws MPException, MPApiException {
//        List<PreferenceItem> items = new ArrayList<>();
//        for (ItemPedido item : pedido.getItens()) {
//            PreferenceItem preferenceItem = PreferenceItem.builder()
//                    .title(item.getTitulo())
//                    .quantity(item.getQuantidade())
//                    .unitPrice(item.getPrecoUnitario())
//                    .build();
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
//}