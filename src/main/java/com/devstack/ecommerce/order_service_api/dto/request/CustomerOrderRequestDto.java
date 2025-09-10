package com.devstack.ecommerce.order_service_api.dto.request;

import java.sql.Date;
import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class CustomerOrderRequestDto {
        private Date orderDate;
        private double totalAmount;
        private String userId;
        private ArrayList<OrderDetailRequestDto> orderDetails;
    
}
 