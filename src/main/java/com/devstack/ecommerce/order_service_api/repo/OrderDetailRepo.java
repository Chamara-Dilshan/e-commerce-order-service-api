package com.devstack.ecommerce.order_service_api.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.devstack.ecommerce.order_service_api.entity.OrderDetail;

public interface OrderDetailRepo extends JpaRepository<OrderDetail, String> {

    
} 