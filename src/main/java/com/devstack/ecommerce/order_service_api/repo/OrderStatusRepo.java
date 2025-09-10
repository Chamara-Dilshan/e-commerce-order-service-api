package com.devstack.ecommerce.order_service_api.repo;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.devstack.ecommerce.order_service_api.entity.OrderStatus;

public interface OrderStatusRepo extends JpaRepository<OrderStatus, String> {

    @Query(nativeQuery = true, value = "SELECT * FROM order_status WHERE status = ?1")
    public Optional <OrderStatus> findByStatus(String status);
} 