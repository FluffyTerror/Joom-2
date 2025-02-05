package com.FluffyTerror.Joom2.dto;

import com.FluffyTerror.Joom2.model.Category;
import com.FluffyTerror.Joom2.model.Image;
import com.FluffyTerror.Joom2.model.Product;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class ProductDto {
    private Long id;

    private String name;

    private String brand;

    private BigDecimal price;

    private int amount;

    private String description;

    private Category category;

    private List<ImageDto> images;

    /**
     * Преобразует сущность Product в ProductDto.
     *
     * @param product объект Product из базы данных
     * @return заполненный ProductDto
     */
    public static ProductDto toDto(Product product) {
        ProductDto dto = new ProductDto();
        dto.setId(product.getId());
        dto.setName(product.getName());
        if (product.getImages() != null) {
            dto.setImages(product.getImages().stream()
                    .map(ImageDto::toDto)
                    .collect(Collectors.toList()));
        }
        return dto;
    }
}
