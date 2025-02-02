package com.FluffyTerror.Joom2.repository;


import com.FluffyTerror.Joom2.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    void deleteAllByCartId(Long id);
}
