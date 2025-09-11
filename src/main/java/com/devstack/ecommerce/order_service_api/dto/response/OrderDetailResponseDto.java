package com.devstack.ecommerce.order_service_api.dto.response;
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

public class OrderDetailResponseDto {

        private String detailId;
        private String productId;
        private int qty;
        private double unitPrice;
        private double discount;
}
