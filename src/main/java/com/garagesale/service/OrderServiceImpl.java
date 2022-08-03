package com.garagesale.service;

import com.garagesale.domain.Asset;
import com.garagesale.domain.Order;
import com.garagesale.domain.PurchaseReceipt;
import com.garagesale.dto.OrderDTO;
import com.garagesale.enums.Category;
import com.garagesale.exceptions.CardNotAvailableException;
import com.garagesale.exceptions.OrderDoesNotExistException;
import com.garagesale.exceptions.ProductDoesntExistException;
import com.garagesale.mapping.OrderDTOMapping;
import com.garagesale.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final AssetService assetService;
    private final ValidatorService validatorService;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, AssetService assetService, ValidatorService validatorService) {
        this.orderRepository = orderRepository;
        this.assetService = assetService;
        this.validatorService = validatorService;
    }

    @Override
    public Order getOrder() {
        return orderRepository.getOrder();
    }

    @Override
    public Map<Category, Asset> getOrderCart() throws OrderDoesNotExistException {
        if (orderRepository.getOrder() == null) {
            throw new OrderDoesNotExistException("No order available");
        } else return orderRepository.getOrderCart();
    }

    @Override
    public Order createOrder() {
        return orderRepository.createOrder();
    }

    @Override
    public Asset addAssetToCart(Asset asset) throws OrderDoesNotExistException {
        if (orderRepository.getOrder() == null) {
            throw new OrderDoesNotExistException("No order available");
        }
        return orderRepository.addAssetToCart(asset);
    }

    @Override
    public PurchaseReceipt finalizeOrder(OrderDTO orderDTO) throws CardNotAvailableException, ProductDoesntExistException, OrderDoesNotExistException {
        if (orderRepository.getOrder() == null) {
            throw new OrderDoesNotExistException("No order available");
        }
        Order order = orderRepository.getOrder();
        order.setCard(OrderDTOMapping.dtoToCreditCard(orderDTO));

        if (assetService.findAllAvailable().size() < orderDTO.getProductID().length) {
            throw new ProductDoesntExistException("Product doesn't exist");
        }
        for (int i = 0; i < orderDTO.getProductID().length; i++) {
            if (assetService.findById(orderDTO.getProductID()[i]).getQuantity() < 1) {
                throw new ProductDoesntExistException("product with ID: " + orderDTO.getProductID()[i] + "  doesnt exist anymore");
            } else order.addAssetToOrderCart(assetService.findById(orderDTO.getProductID()[i]));
        }

        PurchaseReceipt purchaseReceipt = OrderDTOMapping.dtoToPurchaseReceipt(orderDTO);
        purchaseReceipt.setCard(order.getCard());
        purchaseReceipt.setAssetList(order.getPurchaseCart().values().stream().toList());

        int totalBalance = 0;
        for (Asset asset : purchaseReceipt.getAssetList()) {
            //Add to total price
            totalBalance += asset.getPrice();
        }
        purchaseReceipt.setTotalAmount(totalBalance);

        if (!validatorService.validateCardDetails(purchaseReceipt.getCard())) {
            throw new CardNotAvailableException("Card details are not good or expired");
        } else if (!validatorService.validatePurchaseDetails(purchaseReceipt, order))
            throw new CardNotAvailableException("Insufficient balance");
        else {
            purchaseReceipt.setPaymentDetails("payed by Creditcard: " + purchaseReceipt.getCard().getCardNumber());
            return purchaseReceipt;
        }
    }

}
