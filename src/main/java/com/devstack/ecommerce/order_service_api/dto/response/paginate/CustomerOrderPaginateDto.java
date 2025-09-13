package com.devstack.ecommerce.order_service_api.dto.response.paginate;
import com.devstack.ecommerce.order_service_api.dto.response.CustomerOrderResponseDto;
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

public class CustomerOrderPaginateDto {
    private long count;
    private List<CustomerOrderResponseDto> dataList;
}
