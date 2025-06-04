package com.household.purchases.controller.implementation;

import com.household.purchases.controller.DishApi;
import com.household.purchases.dto.dish.CreateDishDto;
import com.household.purchases.dto.dish.DishShortDto;
import com.household.purchases.dto.dish.DishDto;
import com.household.purchases.dto.dish.UpdateDishDto;
import com.household.purchases.dto.dishingredient.CreateDishIngredientDto;
import com.household.purchases.dto.dishingredient.DishIngredientDto;
import com.household.purchases.dto.dishingredient.UpdateDishIngredientDto;
import com.household.purchases.service.DishService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<Page<DishShortDto>> getAll(Pageable pageable) {
        log.info("GET request for all dishes with pageable");
        Page<DishShortDto> result = dishService.getAll(pageable);
        log.info("Returning {} dishes", result.getTotalElements());
        return ResponseEntity.ok(result);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<DishDto> getById(@PathVariable @Positive Long id) {
        log.info("GET request for dish by id: {}", id);
        DishDto result = dishService.getById(id);
        return ResponseEntity.ok(result);
    }

    @Override
    @PostMapping
    public ResponseEntity<DishDto> create(@RequestBody @Valid CreateDishDto dto) {
        log.info("POST request to create dish: {}", dto);
        return ResponseEntity.ok(dishService.create(dto));
    }

    @Override
    @PatchMapping("/{id}")
    public ResponseEntity<DishDto> update(@PathVariable @Positive Long id,
                                          @RequestBody @Valid UpdateDishDto dto) {
        log.info("PATCH request to update dish with id {}: {}", id, dto);
        DishDto result = dishService.update(id, dto);
        return ResponseEntity.ok(result);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable @Positive Long id) {
        log.info("DELETE request for dish with id: {}", id);
        dishService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // --------------------------------------------------------------------------------
    //    DishIngredient Api
    // --------------------------------------------------------------------------------

    @Override
    @PostMapping("/{dishId}/dish-ingredients")
    public ResponseEntity<DishIngredientDto> createDishIngredient(@PathVariable @Positive Long dishId,
                                                                  @RequestBody @Valid CreateDishIngredientDto dto) {
        log.info("POST request to add ingredient to dish with dishId {}: {}", dishId, dto);
        DishIngredientDto result = dishService.createIngredient(dishId, dto);
        return ResponseEntity.ok(result);
    }

    @Override
    @PutMapping("/{dishId}/dish-ingredients/{ingredientId}")
    public ResponseEntity<DishIngredientDto> updateDishIngredient(@PathVariable @Positive Long dishId,
                                                                  @PathVariable @Positive Long ingredientId,
                                                                  @RequestBody @Valid UpdateDishIngredientDto dto) {
        log.info("PUT request to update ingredient of dish with dishId {} and ingredientId {}: {}",
                dishId, ingredientId, dto);
        DishIngredientDto result = dishService.updateIngredient(dishId, ingredientId, dto);
        return ResponseEntity.ok(result);
    }

    @Override
    @DeleteMapping("/{dishId}/dish-ingredients/{dishIngredientId}")
    public ResponseEntity<Void> deleteDishIngredient(@PathVariable @Positive Long dishId,
                                                     @PathVariable @Positive Long dishIngredientId) {
        log.info("DELETE request for dishIngredient with id: {}", dishIngredientId);
        dishService.deleteIngredient(dishId, dishIngredientId);
        return ResponseEntity.noContent().build();
    }
}
