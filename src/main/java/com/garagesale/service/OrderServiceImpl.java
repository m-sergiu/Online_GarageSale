package com.garagesale.service;

import com.garagesale.domain.Asset;
import com.garagesale.domain.Order;
import com.garagesale.domain.PurchaseReceipt;
import com.garagesale.dto.OrderDTO;
import com.garagesale.exceptions.CardNotAvailableException;
import com.garagesale.exceptions.OrderDoesNotExistException;
import com.garagesale.exceptions.ProductAlreadyInCartException;
import com.garagesale.exceptions.ProductDoesntExistException;
import com.garagesale.mapping.OrderDTOMapping;
import com.garagesale.repository.AssetRepository;
import com.garagesale.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final AssetRepository assetRepository;
    private final AssetService assetService;
    private final ValidatorService validatorService;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, AssetRepository assetRepository, AssetService assetService, ValidatorService validatorService) {
        this.orderRepository = orderRepository;
        this.assetRepository = assetRepository;
        this.assetService = assetService;
        this.validatorService = validatorService;
    }

    @Override
    public PurchaseReceipt finalizeOrder(OrderDTO orderDTO) throws CardNotAvailableException, ProductDoesntExistException, OrderDoesNotExistException {

        if (!validatorService.cardValidate(orderDTO.getCard())) {
            throw new CardNotAvailableException("Card details are not good or expired");
        }
        Order order = OrderDTOMapping.dtoToOrder(orderDTO);
        List<Asset> assetList = new ArrayList<>();
        //loop through assets-dtoProductID
        for (int i = 0; i < orderDTO.getProductID().length; i++) {
            Asset asset = assetService.findById((long) orderDTO.getProductID()[i]);
            if (asset.getQuantity() < 1) {
                throw new ProductDoesntExistException("product with ID: " + orderDTO.getProductID()[i] + "  doesnt exist anymore");
            } else {
                //Check for same category
                for (Asset listAsset : assetList) {
                    if (asset.getCategory() == listAsset.getCategory())
                        throw new ProductAlreadyInCartException("You already have 1 item of type: " + asset.getCategory() + " in your cart.");
                } order.setPurchaseBalance(order.getPurchaseBalance() + asset.getPrice());
                assetList.add(asset);
                asset.setQuantity(asset.getQuantity() - 1);
                assetRepository.save(asset);
            }
        } if (order.getPurchaseBalance() > order.getCard().getBalance())
            throw new CardNotAvailableException("Insufficient balance");
        order.setAssets(assetList);
        orderRepository.save(order);

        PurchaseReceipt purchaseReceipt = new PurchaseReceipt();
        purchaseReceipt.setCustomerName(order.getCustomerName());
        purchaseReceipt.setCard(order.getCard());
        purchaseReceipt.setAssets(order.getAssets());
        purchaseReceipt.setTotalAmount(order.getPurchaseBalance());
        purchaseReceipt.setPaymentDetails("payed by Creditcard: " + purchaseReceipt.getCard().getCardNumber());
        return purchaseReceipt;

    }

}
