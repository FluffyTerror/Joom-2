package com.FluffyTerror.Joom2.dto;

import com.FluffyTerror.Joom2.model.Cart;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Set;
import java.util.stream.Collectors;

@Data
public class CartDto {
    private Long cartId;
    private Set<CartItemDto> items;
    private BigDecimal totalAmount;
    /**
     * Преобразует сущность Cart в CartDto.
     *
     * @param cart объект Cart из базы данных
     * @return заполненный CartDto
     */
    public static CartDto toDto(Cart cart) {
        CartDto dto = new CartDto();
        dto.setCartId(cart.getId()); // Используем setCartId, а не setId
        dto.setTotalAmount(cart.getTotalAmount());
        dto.setItems(cart.getItems().stream()
                .map(CartItemDto::toDto)
                .collect(Collectors.toSet())); // Собираем в Set
        return dto;
    }
}
