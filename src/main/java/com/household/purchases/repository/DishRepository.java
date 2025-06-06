package com.household.purchases.repository;

import com.household.purchases.model.Dish;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DishRepository extends JpaRepository<Dish, Long> {

    boolean existsByNameIgnoreCase(@NotBlank String name);
}
