package com.aswemake.market.order.application.service;

import com.aswemake.market.coupon.application.strategy.CouponStrategy;
import com.aswemake.market.coupon.application.strategy.CouponStrategyFactory;
import com.aswemake.market.coupon.domain.repository.CouponRepository;
import com.aswemake.market.coupon.infrastructure.dto.response.GetCouponResponseDto;
import com.aswemake.market.exception.exception.CouponNotFoundException;
import com.aswemake.market.exception.exception.EmptyOrderListException;
import com.aswemake.market.order.domain.repository.OrderRepository;
import com.aswemake.market.order.domain.vo.DeliveryTips;
import com.aswemake.market.order.infrastructure.dto.request.GetOrderAmountRequestDto;
import com.aswemake.market.order.infrastructure.dto.response.GetOrderDetailsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderAmountService {

    private final OrderRepository orderRepository;
    private final CouponRepository couponRepository;
    private final CouponStrategyFactory couponStrategyFactory;

    /**
     * CRUD 를 구현할 경우 Order Entity 에 amount, discountAmount 필드를 추가하여
     * orderDetails 의 합계를 application 레벨에서 계산 후
     * Order 객체 생성시점에 전달하는 방식으로 구현 가능
     */
    @Transactional(readOnly = true)
    public Double getOrderAmount(Long orderId, GetOrderAmountRequestDto getOrderAmountRequestDto) {

        double amount = calculateTotalOrderAmount(orderId);
        double deliveryTips = DeliveryTips.of(amount).deliveryTips();

        if (isCountIdNotNull(getOrderAmountRequestDto)) {

            GetCouponResponseDto coupon = getCoupon(getOrderAmountRequestDto);
            CouponStrategy strategy = couponStrategyFactory.getStrategy(coupon.getCouponPolicy());

            return strategy.applyDiscount(amount, coupon.getDiscountValue());
        }
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

    @Transactional(readOnly = true)
    protected GetCouponResponseDto getCoupon(GetOrderAmountRequestDto getOrderAmountRequestDto) {
        return couponRepository.getCoupon(getOrderAmountRequestDto.couponId())
                .orElseThrow(CouponNotFoundException::new);
    }

    private boolean isCountIdNotNull(GetOrderAmountRequestDto getOrderAmountRequestDto) {
        return getOrderAmountRequestDto.couponId() != null;
    }
}
