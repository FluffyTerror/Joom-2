package com.FluffyTerror.Joom2.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Blob;

@Getter //авто генерация геттеров
@Setter //авто генерация сеттеров
//в моделях нужно использовать эти аннотации, так как @Data не безопасна для авто-генерации в данном классе по причине того,
// что мы добавляем помимо геттеров и сеттеров еще и equals() и hashcode()
@NoArgsConstructor  //аннотация для крафта пустого конструктора
@AllArgsConstructor //аннотация для крафта конструктора со всеми полями
@Entity //сущность для бд
public class Image {
    @Id//помечаем то что это первичный ключ
    @GeneratedValue(strategy = GenerationType.IDENTITY)//автоматическая генерация столбца id в бд
    private Long id;
    private String fileName;
    private String fileType;

    @Lob
    private Blob image;
    private String downloadURL;

    @ManyToOne //у одного продукта множество фото
    @JoinColumn(name = "product_id")//при объединении колонок мы задаем имя новой колонке с результатом объединения
    private Product product;
}
