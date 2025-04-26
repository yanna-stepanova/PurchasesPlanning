package com.household.purchases.service.implementation;

import com.household.purchases.dto.ingredient.IngredientDto;
import com.household.purchases.mapper.IngredientMapper;
import com.household.purchases.model.Ingredient;
import com.household.purchases.repository.IngredientRepository;
import com.household.purchases.service.IngredientService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class IngredientServiceImpl implements IngredientService {
    private final IngredientRepository repository;
    private final IngredientMapper mapper;

    @Override
    public Page<IngredientDto> getAll(Pageable pageable) {
        return repository.findAll(pageable)
                .map(this::toDto);
    }

    private IngredientDto toDto(Ingredient ingredient) {
        return mapper.toDto(ingredient);
    }
}
