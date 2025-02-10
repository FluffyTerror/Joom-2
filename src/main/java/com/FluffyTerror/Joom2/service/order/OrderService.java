package com.FluffyTerror.Joom2.service.order;

import com.FluffyTerror.Joom2.enums.OrderStatus;
import com.FluffyTerror.Joom2.exceptions.ResourceNotFoundException;
import com.FluffyTerror.Joom2.model.Cart;
import com.FluffyTerror.Joom2.model.Order;
import com.FluffyTerror.Joom2.model.OrderItem;
import com.FluffyTerror.Joom2.model.Product;
import com.FluffyTerror.Joom2.repository.OrderRepository;
import com.FluffyTerror.Joom2.repository.ProductRepository;
import com.FluffyTerror.Joom2.service.cart.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService implements IOrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final CartService cartService;

    @Override
    public Order placeOrder(Long userId) {
        Cart cart = cartService.getCartByUserId(userId);

        Order order = createOrder(cart);

        List<OrderItem> orderItemsList = createOrderItems(order, cart);

        order.setOrderItems(new HashSet<>(orderItemsList));
        order.setTotalAmount(calculateTotalAmount(orderItemsList));

        Order savedOrder = orderRepository.save(order);

        cartService.clearCart(cart.getId());


        return savedOrder;
    }

    private Order createOrder(Cart cart) {
        Order order = new Order();
        order.setUser(cart.getUser());
        order.setOrderStatus(OrderStatus.PENDING);
        order.setOrderDate(LocalDate.now());
        return order;
    }

    private List<OrderItem> createOrderItems(Order order, Cart cart) {
        return cart.getItems().stream().map(cartItem -> {
            Product product = cartItem.getProduct();
            product.setAmount(product.getAmount() - cartItem.getQuantity());
            productRepository.save(product);
            return new OrderItem(
                    order,
                    product,
                    cartItem.getQuantity(),
                    cartItem.getUnitPrice());
        }).toList();
    }

    private BigDecimal calculateTotalAmount(List<OrderItem> orderItemList) {
        return orderItemList.stream()
                .map(item -> item.getPrice()
                        .multiply(new BigDecimal(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

    }

    @Override
    public Order getOrder(Long orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found"));
    }
    @Override
    public List<Order> getUserOrders(Long userId){
        return orderRepository.findByUserId(userId);
    }
}
