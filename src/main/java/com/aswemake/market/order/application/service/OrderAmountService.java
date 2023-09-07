package com.aswemake.market.order.application.service;

import com.aswemake.market.exception.exception.EmptyOrderListException;
import com.aswemake.market.order.domain.repository.OrderRepository;
import com.aswemake.market.order.domain.vo.DeliveryTips;
import com.aswemake.market.order.infrastructure.dto.response.GetOrderDetailsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderAmountService {

    private final OrderRepository orderRepository;

    /**
     * CRUD를 구현할 경우 Order 에 amount 필드를 추가하여
     * Order 객체 생성시점에 application 에서 계산된
     * orderId가 같은 orderDetails 의 합계를 전달하는 방식으로 구현 가능
     */
    @Transactional(readOnly = true)
    public Integer getOrderAmount(Long orderId) {

        Integer amount = calculateTotalOrderAmount(orderId);

        DeliveryTips deliveryTipsObject = DeliveryTips.of(amount);
        Integer deliveryTips = deliveryTipsObject.deliveryTips();

        return amount + deliveryTips;
    }

    @Transactional(readOnly = true)
    protected Integer calculateTotalOrderAmount(Long orderId) {

        List<GetOrderDetailsResponseDto> orderDetails = getOrderDetails(orderId);

        return orderDetails.stream()
                .mapToInt(orderDetail -> orderDetail.getPrice() * orderDetail.getQuantity())
                .sum();
    }

    @Transactional(readOnly = true)
    protected List<GetOrderDetailsResponseDto> getOrderDetails(Long orderId) {

        List<GetOrderDetailsResponseDto> orderDetails = orderRepository.getOrderDetails(orderId);
        if (orderDetails.isEmpty()) {
            throw new EmptyOrderListException();
        }
        return orderDetails;
    }
}
