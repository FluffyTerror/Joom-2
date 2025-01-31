package com.FluffyTerror.Joom2.request;

import com.FluffyTerror.Joom2.model.Category;
import lombok.Data;

import java.math.BigDecimal;
@Data
public class UpdateProductRequest {
    private Long id;
    private String name;
    private String brand;
    private BigDecimal price;
    private int amount;
    private String description;
    private Category category;
}
