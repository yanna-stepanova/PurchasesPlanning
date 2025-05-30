package com.household.purchases.repository;

import com.household.purchases.model.MenuDish;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuDishRepository extends JpaRepository<MenuDish, Long> {
}
