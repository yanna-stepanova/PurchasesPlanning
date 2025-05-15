package com.household.purchases.service.implementation;

import com.household.purchases.dto.ingredient.CreateIngredientDto;
import com.household.purchases.dto.ingredient.IngredientDto;
import com.household.purchases.dto.ingredient.UpdateIngredientDto;
import com.household.purchases.exception.NotFoundException;
import com.household.purchases.mapper.IngredientMapper;
import com.household.purchases.model.Ingredient;
import com.household.purchases.repository.IngredientRepository;
import com.household.purchases.service.IngredientService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Service implementation for managing ingredients.
 */
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

    @Override
    public IngredientDto getById(Long id) {
        Ingredient ingredient = getEntityById(id);
        return toDto(ingredient);
    }

    @Override
    @Transactional
    public IngredientDto create(CreateIngredientDto dto) {
        Ingredient ingredient = mapper.toModel(dto);
        return toDto(repository.save(ingredient));
    }

    @Override
    public IngredientDto update(Long id, UpdateIngredientDto dto) {
        Ingredient ingredient = getEntityById(id);
        return toDto(repository.save(mapper.updateFromDto(ingredient, dto)));
    }

    @Override
    public void delete(Long id) {
        Ingredient ingredient = getEntityById(id);
        repository.delete(ingredient);
    }

    private IngredientDto toDto(Ingredient ingredient) {
        return mapper.toDto(ingredient);
    }

    private Ingredient getEntityById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Ingredient", id));
    }
}
