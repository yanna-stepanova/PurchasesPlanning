package com.household.purchases.controller.implementation;

import com.household.purchases.controller.IngredientApi;
import com.household.purchases.dto.ingredient.IngredientDto;
import com.household.purchases.dto.ingredient.IngredientRequest;
import com.household.purchases.service.IngredientService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/ingredients")
@RequiredArgsConstructor
@Validated
public class IngredientController implements IngredientApi {
    private final IngredientService ingredientService;

    @Override
    @GetMapping
    public ResponseEntity<Page<IngredientDto>> getAll(@PageableDefault(size = 10) Pageable pageable) {
        return ResponseEntity.ok(ingredientService.getAll(pageable));
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<IngredientDto> getById(Long id) {
        return ResponseEntity.ok(ingredientService.getById(id));
    }

    @Override
    @PostMapping
    public ResponseEntity<IngredientDto> create(IngredientRequest request) {
        return ResponseEntity.ok(ingredientService.create(request));
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<IngredientDto> update(Long id,
                                                IngredientRequest request) {
        return ResponseEntity.ok(ingredientService.update(id, request));
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(Long id) {
        ingredientService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
