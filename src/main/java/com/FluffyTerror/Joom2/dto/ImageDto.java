package com.FluffyTerror.Joom2.dto;

import com.FluffyTerror.Joom2.model.Image;
import lombok.Data;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Data
public class ImageDto {
    private Long id;
    private String fileName;
    private String downloadUrl;

    //очередной костыль для работы корзины который берет изображение, но не возвращает blob, а только ссылку на скачивание
    public static ImageDto toDto(Image image) {
        ImageDto dto = new ImageDto();
        dto.setId(image.getId());
        dto.setFileName(image.getFileName());

        // Формируем ссылку для скачивания
        String downloadUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/v1/images/image/download/")
                .path(image.getId().toString())
                .toUriString();
        dto.setDownloadUrl(downloadUrl);

        return dto;
    }
}
