package com.garagesale.factory;

import com.garagesale.dto.OrderDTO;

public interface AbstractOrderFactory<T> {
    T create(OrderDTO orderDTO);
}
