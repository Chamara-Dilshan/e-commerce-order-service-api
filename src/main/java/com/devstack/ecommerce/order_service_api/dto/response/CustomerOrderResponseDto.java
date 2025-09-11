package com.devstack.ecommerce.order_service_api.dto.response;
import java.sql.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class CustomerOrderResponseDto {
        private String orderId;
        private Date orderDate;
        private double totalAmount;
        private String status;
        private String userId;
        private String remark;
        private List<OrderDetailResponseDto> orderDetails;
    
}
