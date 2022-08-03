package com.garagesale.service;

import com.garagesale.domain.PurchaseReceipt;
import com.garagesale.dto.OrderDTO;

public interface OrderService {
    PurchaseReceipt finalizeOrder(OrderDTO orderDTO);
}
