package com.FluffyTerror.Joom2.dto;

import com.FluffyTerror.Joom2.model.Cart;
import com.FluffyTerror.Joom2.model.CartItem;
import com.FluffyTerror.Joom2.model.Product;
import lombok.Data;

import java.math.BigDecimal;
import java.util.stream.Collectors;

@Data
public class CartItemDto {
    private Long itemId;
    private Integer quantity;
    private BigDecimal unitPrice;
    private ProductDto product;


    /**
     * Преобразует сущность CartItem в CartItemDto.
     *
     * @param item объект CartItem из базы данных
     * @return заполненный CartItemDto
     */
    public static CartItemDto toDto(CartItem item) {
        CartItemDto dto = new CartItemDto();
        // Предполагаем, что в вашей сущности CartItem идентификатор называется getId()
        dto.setItemId(item.getId());
        dto.setQuantity(item.getQuantity());
        // Предполагаем, что цена за единицу хранится в поле unitPrice
        dto.setUnitPrice(item.getUnitPrice());
        // Преобразуем продукт в DTO через вызов статического метода в ProductDto
        dto.setProduct(ProductDto.toDto(item.getProduct()));
        return dto;
    }
}
