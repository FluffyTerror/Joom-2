package com.FluffyTerror.Joom2.dto;

import com.FluffyTerror.Joom2.model.Category;
import com.FluffyTerror.Joom2.model.Image;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
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
}
