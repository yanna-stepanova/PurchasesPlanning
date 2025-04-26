package com.household.purchases.controller.implementation;

import com.household.purchases.controller.IngredientApi;
import com.household.purchases.dto.ingredient.IngredientDto;
import com.household.purchases.service.IngredientService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/ingredients")
@RequiredArgsConstructor
public class IngredientController implements IngredientApi {
    private final IngredientService ingredientService;
    @Override
    public ResponseEntity<Page<IngredientDto>> getAll(@PageableDefault(size = 10) Pageable pageable) {
        return ResponseEntity.ok(ingredientService.getAll(pageable));
    }
}
