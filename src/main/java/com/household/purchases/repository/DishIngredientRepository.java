package com.household.purchases.repository;

import com.household.purchases.model.DishIngredient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DishIngredientRepository extends JpaRepository<DishIngredient, Long> {

    Optional<DishIngredient> findByDishIdAndIngredientId(Long dishId, Long ingredientId);

    Optional<DishIngredient> findByIdAndDishId(Long dishIngredientId, Long dishId);

    List<DishIngredient> findAllByDishId(Long dishId);
}
