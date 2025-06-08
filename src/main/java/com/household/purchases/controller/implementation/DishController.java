package com.household.purchases.controller.implementation;

import com.household.purchases.controller.DishApi;
import com.household.purchases.dto.dish.CreateDishDto;
import com.household.purchases.dto.dish.DishShortDto;
import com.household.purchases.dto.dish.DishDto;
import com.household.purchases.dto.dish.UpdateDishDto;
import com.household.purchases.dto.dishingredient.CreateDishIngredientDto;
import com.household.purchases.dto.dishingredient.DishIngredientDto;
import com.household.purchases.dto.dishingredient.DishIngredientShortDto;
import com.household.purchases.dto.dishingredient.UpdateDishIngredientDto;
import com.household.purchases.service.DishService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Implementation of Dish API.
 */
@Slf4j
@RestController
@RequestMapping("${api.base-path}/dishes")
@RequiredArgsConstructor
@Validated
public class DishController implements DishApi {
    private final DishService dishService;

    @Override
    @GetMapping
    public ResponseEntity<Page<DishShortDto>> getAllDishes(Pageable pageable) {
        log.info("GET request for all dishes with pageable");
        Page<DishShortDto> result = dishService.getAllDishes(pageable);
        log.info("Returning {} dishes", result.getTotalElements());
        return ResponseEntity.ok(result);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<DishDto> getDishById(Long id) {
        log.info("GET request for dish by id: {}", id);
        DishDto result = dishService.getDishById(id);
        return ResponseEntity.ok(result);
    }

    @Override
    @PostMapping
    public ResponseEntity<DishDto> createDish(CreateDishDto dto) {
        log.info("POST request to create dish: {}", dto);
        return ResponseEntity.ok(dishService.createDish(dto));
    }

    @Override
    @PatchMapping("/{id}")
    public ResponseEntity<DishDto> updateDish(Long id, UpdateDishDto dto) {
        log.info("PATCH request to update dish with id {}: {}", id, dto);
        DishDto result = dishService.updateDish(id, dto);
        return ResponseEntity.ok(result);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDish(Long id) {
        log.info("DELETE request for dish with id: {}", id);
        dishService.deleteDish(id);
        return ResponseEntity.noContent().build();
    }

    // --------------------------------------------------------------------------------
    //    DishIngredient Api
    // --------------------------------------------------------------------------------

    @Override
    @PostMapping("/{dishId}/dish-ingredients")
    public ResponseEntity<DishIngredientDto> createDishIngredient(Long dishId,
                                                                  CreateDishIngredientDto dto) {
        log.info("POST request to add ingredient to dish with dishId {}: {}", dishId, dto);
        DishIngredientDto result = dishService.createIngredient(dishId, dto);
        return ResponseEntity.ok(result);
    }

    @Override
    @PutMapping("/{dishId}/dish-ingredients/{ingredientId}")
    public ResponseEntity<DishIngredientDto> updateDishIngredient(Long dishId,
                                                                  Long ingredientId,
                                                                  UpdateDishIngredientDto dto) {
        log.info("PUT request to update ingredient of dish with dishId {} and ingredientId {}: {}",
                dishId, ingredientId, dto);
        DishIngredientDto result = dishService.updateIngredient(dishId, ingredientId, dto);
        return ResponseEntity.ok(result);
    }

    @Override
    @DeleteMapping("/{dishId}/dish-ingredients/{dishIngredientId}")
    public ResponseEntity<Void> deleteDishIngredient(Long dishId, Long dishIngredientId) {
        log.info("DELETE request for dishIngredient with id: {}", dishIngredientId);
        dishService.deleteIngredient(dishId, dishIngredientId);
        return ResponseEntity.noContent().build();
    }

    @Override
    @GetMapping("/{dishId}/dish-ingredients")
    public ResponseEntity<List<DishIngredientShortDto>> getAllDishIngredient(Long dishId) {
        log.info("GET request for all ingredients of dish with id: {}", dishId);
        List<DishIngredientShortDto> result = dishService.getAllIngredientsByDish(dishId);
        return ResponseEntity.ok(result);
    }
}
