package com.household.purchases.controller.implementation;

import com.household.purchases.controller.IngredientApi;
import com.household.purchases.dto.ingredient.IngredientDto;
import com.household.purchases.dto.ingredient.IngredientRequest;
import com.household.purchases.service.IngredientService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
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

@RestController
@RequestMapping("/api/v1/ingredients")
@RequiredArgsConstructor
@Validated
public class IngredientController {
    private final IngredientService ingredientService;

    @GetMapping
    public ResponseEntity<Page<IngredientDto>> getAll(@ParameterObject @PageableDefault(size = 10) Pageable pageable) {
        return ResponseEntity.ok(ingredientService.getAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<IngredientDto> getById(@PathVariable @Positive Long id) {
        return ResponseEntity.ok(ingredientService.getById(id));
    }

    @PostMapping
    public ResponseEntity<IngredientDto> create(@RequestBody @Valid IngredientRequest request) {
        return ResponseEntity.ok(ingredientService.create(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<IngredientDto> update(@PathVariable @Positive Long id,
                                                @RequestBody @Valid IngredientRequest request) {
        return ResponseEntity.ok(ingredientService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable @Positive Long id) {
        ingredientService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
