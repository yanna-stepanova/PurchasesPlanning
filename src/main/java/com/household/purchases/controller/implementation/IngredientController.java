package com.household.purchases.controller.implementation;

import com.household.purchases.controller.IngredientApi;
import com.household.purchases.dto.ingredient.CreateIngredientDto;
import com.household.purchases.dto.ingredient.IngredientDto;
import com.household.purchases.dto.ingredient.UpdateIngredientDto;
import com.household.purchases.service.IngredientService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Implementation of Ingredient API.
 */
@Slf4j
@RestController
@RequestMapping("${api.base-path}/ingredients")
@RequiredArgsConstructor
@Validated
public class IngredientController implements IngredientApi {
    private final IngredientService ingredientService;

    @Override
    @GetMapping
    public ResponseEntity<Page<IngredientDto>> getAll(@ParameterObject @PageableDefault(size = 10) Pageable pageable) {
        log.info("GET request for all ingredients with pageable");
        Page<IngredientDto> result = ingredientService.getAll(pageable);
        log.info("Returning {} ingredients", result.getTotalElements());
        return ResponseEntity.ok(result);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<IngredientDto> getById(@PathVariable @Positive Long id) {
        log.info("GET request for ingredient by id: {}", id);
        IngredientDto result = ingredientService.getById(id);
        return ResponseEntity.ok(result);
    }

    @Override
    @PostMapping
    public ResponseEntity<IngredientDto> create(@RequestBody @Valid CreateIngredientDto dto) {
        log.info("POST request to create ingredient: {}", dto);
        return ResponseEntity.ok(ingredientService.create(dto));
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<IngredientDto> update(@PathVariable @Positive Long id,
                                                @RequestBody @Valid UpdateIngredientDto dto) {
        log.info("PUT request to update ingredient with id {}: {}", id, dto);
        IngredientDto result = ingredientService.update(id, dto);
        return ResponseEntity.ok(result);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable @Positive Long id) {
        log.info("DELETE request for ingredient with id: {}", id);
        ingredientService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
