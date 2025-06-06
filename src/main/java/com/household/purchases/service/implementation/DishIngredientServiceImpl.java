package com.household.purchases.service.implementation;

import com.household.purchases.dto.dishingredient.CreateDishIngredientDto;
import com.household.purchases.dto.dishingredient.DishIngredientDto;
import com.household.purchases.dto.dishingredient.DishIngredientShortDto;
import com.household.purchases.dto.dishingredient.UpdateDishIngredientDto;
import com.household.purchases.exception.NotFoundException;
import com.household.purchases.mapper.DishIngredientMapper;
import com.household.purchases.model.Dish;
import com.household.purchases.model.DishIngredient;
import com.household.purchases.model.Ingredient;
import com.household.purchases.repository.DishIngredientRepository;
import com.household.purchases.service.DishIngredientService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DishIngredientServiceImpl implements DishIngredientService {
    private final DishIngredientRepository repository;
    private final DishIngredientMapper mapper;

    @Override
    @Transactional
    public DishIngredient create(Dish dish, Ingredient ingredient, CreateDishIngredientDto dto) {
        DishIngredient entity = mapper.toModel(dto);
        entity.setDish(dish);
        entity.setIngredient(ingredient);
        return repository.save(entity);
    }

    @Override
    public DishIngredient getByDishAndIngredient(Long dishId, Long ingredientId) {
        return repository.findByDishIdAndIngredientId(dishId, ingredientId).orElseThrow(() ->
                new NotFoundException("DishIngredient", "dishId", dishId, "ingredientId", ingredientId));
    }

    @Override
    public DishIngredientDto update(Long dishId, Long ingredientId, UpdateDishIngredientDto dto) {
        DishIngredient dishIngredient = getByDishAndIngredient(dishId, ingredientId);
        mapper.updateFromDto(dishIngredient, dto);
        repository.save(dishIngredient);
        return mapper.toDto(dishIngredient);
    }

    @Override
    public DishIngredientDto toDto(DishIngredient dishIngredient) {
        return mapper.toDto(dishIngredient);
    }

    @Override
    public void delete(Long dishId, Long dishIngredientId) {
        DishIngredient dishIngredient = repository.findByIdAndDishId(dishIngredientId, dishId)
                .orElseThrow(() -> new NotFoundException("DishIngredient", "id", dishIngredientId, "dishId", dishId));
        repository.delete(dishIngredient);
    }

    @Override
    public List<DishIngredientShortDto> getAllByDish(Long dishId) {
        List<DishIngredient> ingredientList= repository.findAllByDishId(dishId);
        return ingredientList.stream()
                .map(mapper::toShortDto)
                .toList();
    }
}
