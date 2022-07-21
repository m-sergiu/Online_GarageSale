package com.garagesale.service;

import com.garagesale.domain.Asset;
import com.garagesale.domain.CreditCard;
import com.garagesale.domain.User;
import com.garagesale.exceptions.CreditCardNotAvailable;
import com.garagesale.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public String finalizePurchase() throws CreditCardNotAvailable {
        List<Asset> list = orderRepository.getAll();
        StringBuilder result = new StringBuilder();
        int totalBalance = 0;
        result.append("Thank you for the purchase. The item list: ");
        for (Asset asset : list) {
            //Add to total price
            totalBalance += asset.getPrice();
            result.append(asset.getAssetName() + " - $" + asset.getPrice() + "; ");
        }
        result.append(" Total balance: $" + totalBalance);

        if (!cardValidate(getCreditCard())){
            throw new CreditCardNotAvailable("Credit card doesn't exist");
        } else if(getCreditCard().getBalance() < totalBalance)
            throw new CreditCardNotAvailable("Insufficient balance");
            else {
            result.append("payed by credit card: " + getCreditCard().getCardNumber());
            return result.toString();
        }
    }

    public boolean cardValidate(CreditCard creditCard){
        return  creditCard.getCardNumber().length() == 16 && creditCard.getCIV().length() == 3;
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
