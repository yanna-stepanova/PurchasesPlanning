package com.household.purchases.service.implementation;

import com.household.purchases.dto.ingredient.IngredientDto;
import com.household.purchases.dto.ingredient.IngredientRequest;
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
    public Page<IngredientDto> getAll(Pageable pageable, String nameFilter) {
        return repository.findAllByNameContainingIgnoreCase(nameFilter, pageable)
                .map(this::toDto);
    }

    @Override
    public IngredientDto getById(Long id) {
        Ingredient ingredient = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Ingredient", id));
        return toDto(ingredient);
    }

    @Override
    @Transactional
    public IngredientDto create(IngredientRequest request) {
        Ingredient ingredient = mapper.toModel(request);
        return toDto(repository.save(ingredient));
    }

    @Override
    public IngredientDto update(Long id, IngredientRequest request) {
        Ingredient ingredient = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Ingredient", id));
        return toDto(repository.save(mapper.updateFromDto(ingredient, request)));
    }

    @Override
    public void delete(Long id) {
        Ingredient ingredient = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Ingredient", id));
        repository.delete(ingredient);
    }

    private IngredientDto toDto(Ingredient ingredient) {
        return mapper.toDto(ingredient);
    }
}
