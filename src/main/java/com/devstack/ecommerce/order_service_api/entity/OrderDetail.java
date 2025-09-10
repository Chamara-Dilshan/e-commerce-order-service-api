package com.devstack.ecommerce.order_service_api.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "order_detail")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class OrderDetail {
    @Id
    @Column(name = "detail_id", unique = true ,nullable = false, length = 80)
    private String detailId;

    @Column(name = "product_id", nullable = false, length = 80)
    private String productId;

    @Column(name = "qty", nullable = false)
    private int qty;

    @Column(name = "unit_price", nullable = false, precision = 10, scale = 2)
    private double unitPrice;

    @Column(name = "discount", precision = 10, scale = 2)
    private double discount;

    @Column(name = "user_id", nullable = false, length = 80)
    private String userId;

    @ManyToOne
    @JoinColumn(name = "customer_order_id")
    private CustomerOrder customerOrder;
    
}
