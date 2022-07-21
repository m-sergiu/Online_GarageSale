package com.garagesale.service;

import com.garagesale.domain.Asset;
import com.garagesale.domain.CreditCard;
import com.garagesale.domain.PurchaseReceipt;
import com.garagesale.domain.User;
import com.garagesale.exceptions.CreditCardNotAvailable;
import com.garagesale.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    private OrderRepository orderRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }
    @Override
    public List<Asset> getAll() {
        return orderRepository.getAll();
    }

    @Override
    public String createOrder() {
        return orderRepository.createOrder();
    }

    @Override
    public String addAssetToCart() {
        return orderRepository.addAssetToCart();
    }

    @Override
    public PurchaseReceipt finalizePurchase() throws CreditCardNotAvailable {
        PurchaseReceipt purchaseReceipt = new PurchaseReceipt();
        purchaseReceipt.setCustomerName(getUser().getUsername());
        purchaseReceipt.setCustomerEmail(getUser().getEmail());
        purchaseReceipt.setAssetList(orderRepository.getAll());
        purchaseReceipt.setCreditCard(getCreditCard());
        int totalBalance = 0;
        for (Asset asset : orderRepository.getAll()) {
            //Add to total price
            totalBalance += asset.getPrice();
        }
        purchaseReceipt.setTotalAmount(totalBalance);

        if (!cardValidate(purchaseReceipt.getCreditCard())){
            throw new CreditCardNotAvailable("Credit card details are not good or expired");
        } else if(purchaseReceipt.getTotalAmount() < totalBalance)
            throw new CreditCardNotAvailable("Insufficient balance");
            else {
                purchaseReceipt.setPaymentDetails("payed by card: " + purchaseReceipt.getCreditCard().getCardNumber());
                return purchaseReceipt;
        }
    }

    public boolean cardValidate(CreditCard creditCard){
        return  creditCard.getCardNumber().length() == 16 && creditCard.getCIV().length() == 3 &&
                creditCard.getExpiry().isAfter(LocalDate.now());
    }
    @Override
    public User getUser() {
        return orderRepository.getUser();
    }

    @Override
    public CreditCard getCreditCard() {
        return orderRepository.getCreditCard();
    }
}
