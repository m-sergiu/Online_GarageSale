package com.garagesale.repository;

import com.garagesale.domain.Orders.PurchaseOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<PurchaseOrder, Long> {

}
