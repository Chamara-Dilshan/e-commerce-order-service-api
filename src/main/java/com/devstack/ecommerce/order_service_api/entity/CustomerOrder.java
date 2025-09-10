package com.devstack.ecommerce.order_service_api.entity;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "customer_order")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class CustomerOrder {
    @Id
    @Column(name = "order_id", unique = true ,nullable = false, length = 80)
    private String orderId;

    @Column(name = "order_date", nullable = false, columnDefinition = "DATETIME")
    private Date orderDate;

    @Column(name = "total_amount", nullable = false, precision = 10, scale = 2)
    private double totalAmount;

    @Column(name = "user_id", nullable = false, length = 80)
    private String userId;

    @Column(name = "status", length = 750)
    private String remark;
  
    //====================================================
    @OneToMany(mappedBy = "customerOrder")
    private Set<OrderDetail> products = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "order_status_id")
    private OrderStatus orderStatus;
}
