package com.devstack.ecommerce.order_service_api.service.impl;

import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.devstack.ecommerce.order_service_api.dto.request.CustomerOrderRequestDto;
import com.devstack.ecommerce.order_service_api.dto.request.OrderDetailRequestDto;
import com.devstack.ecommerce.order_service_api.entity.CustomerOrder;
import com.devstack.ecommerce.order_service_api.entity.OrderDetail;
import com.devstack.ecommerce.order_service_api.entity.OrderStatus;
import com.devstack.ecommerce.order_service_api.repo.CustomerOrderRepo;
import com.devstack.ecommerce.order_service_api.repo.OrderStatusRepo;
import com.devstack.ecommerce.order_service_api.service.CustomerOrderService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerOrderServiceImpl implements CustomerOrderService {

    private final CustomerOrderRepo customerOrderRepo;
    private final OrderStatusRepo orderStatusRepo;
    @Override
    public void createOrder(CustomerOrderRequestDto requestDto) {

        OrderStatus orderStatus = orderStatusRepo.findByStatus("PENDING").orElseThrow(()-> new RuntimeException("No Such Order Status"));

        CustomerOrder customerOrder = new CustomerOrder();
        customerOrder.setOrderId(UUID.randomUUID().toString());
        customerOrder.setOrderDate(requestDto.getOrderDate());
        customerOrder.setTotalAmount(requestDto.getTotalAmount());
        customerOrder.setUserId(requestDto.getUserId());
        customerOrder.setRemark("");


        customerOrder.setOrderStatus(orderStatus);

    
        customerOrder.setProducts(
            requestDto.getOrderDetails().stream().map(e -> createOrderDetail(e, customerOrder)).collect(Collectors.toSet())
        );
        customerOrderRepo.save(customerOrder);

    }

    private OrderDetail createOrderDetail(OrderDetailRequestDto requestDto, CustomerOrder order) {
        if (requestDto==null) {
            return null;  
        }
        return OrderDetail.builder()
        .detailId(UUID.randomUUID().toString())
        .unitPrice(requestDto.getUnitPrice())
        .discount(requestDto.getDiscount())
        .qty(requestDto.getQty())
        .customerOrder(order)
        .build();

    }
    
}
