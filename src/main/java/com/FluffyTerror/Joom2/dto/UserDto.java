package com.FluffyTerror.Joom2.dto;

import lombok.Data;

import java.util.List;
@Data
public class UserDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private List<OrderDto> orders;
    private CartDto cart;
}
