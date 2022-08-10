package com.garagesale.service;

import com.garagesale.domain.Asset;
import com.garagesale.domain.PurchaseOrder;
import com.garagesale.domain.PurchaseReceipt;
import com.garagesale.dto.OrderDTO;
import com.garagesale.enums.Category;
import com.garagesale.exceptions.CardNotAvailableException;
import com.garagesale.exceptions.OrderDoesNotExistException;
import com.garagesale.exceptions.ProductAlreadyInCartException;
import com.garagesale.exceptions.ProductDoesntExistException;
import com.garagesale.mapping.OrderDTOMapping;
import com.garagesale.repository.AssetRepository;
import com.garagesale.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
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

        if (!validatorService.validateCardDetails(orderDTO.getCard())) {
            throw new CardNotAvailableException("Card details are not good or expired");
        }
        PurchaseOrder purchaseOrder = OrderDTOMapping.dtoToOrder(orderDTO);
        List<Asset> assetList = new ArrayList<>();
        HashMap<Category,Double> receiptList = new HashMap<>();
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
                }
                purchaseOrder.setPurchaseBalance(purchaseOrder.getPurchaseBalance() + asset.getPrice());
                receiptList.put(asset.getCategory(),asset.getPrice());
                assetList.add(asset);
            }
        }
        if (purchaseOrder.getPurchaseBalance() > purchaseOrder.getCard().getBalance()) throw new CardNotAvailableException("Insufficient balance");
        purchaseOrder.setAssets(assetList);
        orderRepository.save(purchaseOrder);
        List<PurchaseOrder> orderList = new ArrayList<>();
        orderList.add(purchaseOrder);

        for (Asset asset : purchaseOrder.getAssets()) {
            asset.setQuantity(asset.getQuantity() - 1);
            asset.setPurchaseOrder(orderList);
            assetRepository.save(asset);
        }
        purchaseOrder.getCard().setPurchaseOrder(purchaseOrder);
        PurchaseReceipt purchaseReceipt = OrderDTOMapping.dtoToPurchaseReceipt(orderDTO);
        purchaseReceipt.setAssets(receiptList);
        purchaseReceipt.setTotalAmount(purchaseOrder.getPurchaseBalance());
        purchaseReceipt.setPaymentDetails("Payed by card: " + purchaseOrder.getCard().getCardNumber());
        return purchaseReceipt;

    }

}
