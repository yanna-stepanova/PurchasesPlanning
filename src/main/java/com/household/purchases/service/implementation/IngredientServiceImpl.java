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
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Service implementation for managing ingredients.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class IngredientServiceImpl implements IngredientService {
    private final IngredientRepository repository;
    private final IngredientMapper mapper;

    @Override
    public Page<IngredientDto> getAll(Pageable pageable) {
        log.info("Retrieving all ingredients with pageable: {}", pageable);
        return repository.findAll(pageable)
                .map(this::toDto);
    }

    @Override
    public IngredientDto getById(Long id) {
        Ingredient ingredient = getEntityById(id);
        log.info("Received ingredient: '{}'", ingredient.getName());
        return toDto(ingredient);
    }

    @Override
    @Transactional
    public IngredientDto create(CreateIngredientDto dto) {
        Ingredient ingredient = mapper.toModel(dto);
        Ingredient saved = repository.save(ingredient);
        log.info("Saved ingredient with id: {}", saved.getId());
        return toDto(saved);
    }

    @Override
    @Transactional
    public IngredientDto update(Long id, UpdateIngredientDto dto) {
        Ingredient ingredient = getEntityById(id);
        Ingredient updated = mapper.updateFromDto(ingredient, dto);
        Ingredient saved = repository.save(updated);
        log.info("Ingredient with id = {} successfully updated", id);
        return toDto(saved);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Ingredient ingredient = getEntityById(id);
        repository.delete(ingredient);
        log.info("Ingredient with id = {} successfully deleted", id);
    }

    private IngredientDto toDto(Ingredient ingredient) {
        return mapper.toDto(ingredient);
    }

    private Ingredient getEntityById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Ingredient", id));
    }
}
