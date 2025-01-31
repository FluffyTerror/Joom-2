package com.FluffyTerror.Joom2.request;


import com.FluffyTerror.Joom2.model.Category;
import lombok.Data;

import java.math.BigDecimal;
@Data//по факту тоже самое что и @Getter и @Setter, но более правильная с точки зрения JVM
public class AddProductRequest { //причина по которой мы добавляем новый класс для запроса на добавление - это возможные проблемы при работе с моделью
    private Long id;
    private String name;
    private String brand;
    private BigDecimal price;
    private int amount;
    private String description;
    private Category category;

}
