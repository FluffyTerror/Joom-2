package com.FluffyTerror.Joom2.repository;

import com.FluffyTerror.Joom2.model.Cart;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
}
