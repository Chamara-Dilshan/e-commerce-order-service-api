package com.devstack.ecommerce.order_service_api.service.impl;

import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import com.devstack.ecommerce.order_service_api.dto.request.CustomerOrderRequestDto;
import com.devstack.ecommerce.order_service_api.dto.request.OrderDetailRequestDto;
import com.devstack.ecommerce.order_service_api.dto.response.CustomerOrderResponseDto;
import com.devstack.ecommerce.order_service_api.dto.response.OrderDetailResponseDto;
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

    @Override
    public CustomerOrderResponseDto findOrderById(String orderId) {
        CustomerOrder customerOrder = customerOrderRepo.findById(orderId)
            .orElseThrow(() -> new RuntimeException(String.format("Order not found with %s",orderId)));
        return toCustomerOrderResponseDto(customerOrder);
    }

    private CustomerOrderResponseDto toCustomerOrderResponseDto(CustomerOrder customerOrder) {
        if (customerOrder == null) {
            return null;
        }
        return CustomerOrderResponseDto.builder()
            .orderId(customerOrder.getOrderId())
            .orderDate(customerOrder.getOrderDate())
            .userId(customerOrder.getUserId())
            .totalAmount(customerOrder.getTotalAmount())
            .orderDetails(
                customerOrder.getProducts().stream().map(this::toOrderDetailResponseDto).collect(Collectors.toList())
            )
            .status(customerOrder.getOrderStatus().getStatus())
            .remark(customerOrder.getRemark())
            .build();
    }

    private OrderDetailResponseDto toOrderDetailResponseDto(OrderDetail orderDetail) {
        if (orderDetail == null) {
            return null;
        }
        return OrderDetailResponseDto.builder()
            .detailId(orderDetail.getDetailId())
            .productId(orderDetail.getProductId())
            .qty(orderDetail.getQty())
            .unitPrice(orderDetail.getUnitPrice())
            .discount(orderDetail.getDiscount())
            .build();
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
