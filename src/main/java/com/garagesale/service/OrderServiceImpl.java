package com.garagesale.service;

import com.garagesale.domain.*;
import com.garagesale.dto.OrderDTO;
import com.garagesale.enums.Category;
import com.garagesale.exceptions.CardNotAvailable;
import com.garagesale.mapping.OrderDTOMapping;
import com.garagesale.repository.AssetRepository;
import com.garagesale.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Map;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final AssetService assetService;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, AssetService assetService) {
        this.orderRepository = orderRepository;
        this.assetService = assetService;
    }

    @Override
    public Order getOrder() {
        return orderRepository.getOrder();
    }

    @Override
    public Map<Category, Asset> getOrderCart() {
        return orderRepository.getOrderCart();
    }

    @Override
    public Order createOrder() {
        return orderRepository.createOrder();
    }

    @Override
    public Asset addAssetToCart(Asset asset) {
        return orderRepository.addAssetToCart(asset);
    }

    @Override
    public PurchaseReceipt finalizeOrder(OrderDTO orderDTO) throws CardNotAvailable {
        Order order = createOrder();
        order.setCreditCard(OrderDTOMapping.dtoToCreditCard(orderDTO));
        order.addAssetToOrderCart(assetService.findById(orderDTO.getProductID()));

        PurchaseReceipt purchaseReceipt = OrderDTOMapping.dtoToPurchaseReceipt(orderDTO);
        purchaseReceipt.setCard(order.getCard());
        purchaseReceipt.setAssetList(order.getPurchaseCart().values().stream().toList());

        int totalBalance = 0;
        for (Asset asset : purchaseReceipt.getAssetList()) {
            //Add to total price
            totalBalance += asset.getPrice();
        }
        purchaseReceipt.setTotalAmount(totalBalance);

        if (!cardValidate(purchaseReceipt.getCard())) {
            throw new CardNotAvailable("Card details are not good or expired");
        } else if (purchaseReceipt.getTotalAmount() > order.getCard().getBalance())
            throw new CardNotAvailable("Insufficient balance");
        else {
            purchaseReceipt.setPaymentDetails("payed by card: " + purchaseReceipt.getCard().getCardNumber());
            return purchaseReceipt;
        }
    }

    public boolean cardValidate(Card card) {
        return card.getCardNumber().length() == 16 && card.getCIV().length() == 3 &&
                card.getExpiry().isAfter(LocalDate.now());
    }

}
