package com.devstack.ecommerce.order_service_api.dto.response;

import java.sql.Date;
import java.util.ArrayList;

import com.devstack.ecommerce.order_service_api.dto.request.OrderDetailRequestDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class CustomerOrderResponseDto {
        private String orderId;
        private Date orderDate;
        private double totalAmount;
        private String status;
        private String userId;
        private String remark;
        private ArrayList<OrderDetailRequestDto> orderDetails;
    
}
