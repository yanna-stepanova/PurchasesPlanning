package com.household.purchases.service.implementation;

import com.household.purchases.dto.dish.CreateDishDto;
import com.household.purchases.dto.dish.DishDto;
import com.household.purchases.dto.dish.DishShortDto;
import com.household.purchases.dto.dish.UpdateDishDto;
import com.household.purchases.dto.dishingredient.CreateDishIngredientDto;
import com.household.purchases.dto.dishingredient.DishIngredientDto;
import com.household.purchases.dto.dishingredient.UpdateDishIngredientDto;
import com.household.purchases.exception.DuplicateResourceException;
import com.household.purchases.exception.NotFoundException;
import com.household.purchases.mapper.DishMapper;
import com.household.purchases.model.Dish;
import com.household.purchases.model.DishIngredient;
import com.household.purchases.model.Ingredient;
import com.household.purchases.repository.DishRepository;
import com.household.purchases.service.DishIngredientService;
import com.household.purchases.service.DishService;
import com.household.purchases.service.IngredientService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Service implementation for managing dishes.
 */
@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class DishServiceImpl implements DishService {
    private final DishRepository repository;
    private final DishMapper mapper;
    private final DishIngredientService dishIngredientService;
    private final IngredientService ingredientService;

    @Override
    public Page<DishShortDto> getAll(Pageable pageable) {
        log.info("Retrieving all dishes with pageable: {}", pageable);
        return repository.findAll(pageable).map(mapper::toShortDto);
    }

    @Override
    public DishDto getById(Long id) {
        Dish dish = getEntityById(id);
        log.info("Received dish: '{}'", dish.getName());
        return mapper.toDto(dish);
    }

    @Override
    public DishDto create(CreateDishDto dto) {
        if (repository.existsByNameIgnoreCase(dto.name())) {
            throw new DuplicateResourceException("Dish", "name", dto.name());
        }
        Dish dish = mapper.toModel(dto);
        repository.save(dish);
        log.info("Saved dish with id: {}", dish.getId());
        List<CreateDishIngredientDto> ingredientDtos = dto.ingredients();
        if (ingredientDtos != null) {
            createDishIngredients(dish, ingredientDtos);
        }
        return mapper.toDto(dish);
    }

    @Override
    public DishDto update(Long id, UpdateDishDto dto) {
        Dish dish = getEntityById(id);
        mapper.patchFromDto(dish, dto);

        if (dto.ingredients() != null && !dto.ingredients().isEmpty()) {
            dish.getIngredients().clear();

            List<DishIngredient> ingredients = new ArrayList<>();
            for (CreateDishIngredientDto ingredientDto : dto.ingredients()) {
                Ingredient ingredient = ingredientService.getEntityById(ingredientDto.ingredientId());
                DishIngredient dishIngredient = dishIngredientService.create(dish, ingredient, ingredientDto);
                ingredients.add(dishIngredient);
            }
            dish.getIngredients().addAll(ingredients);
        }

        repository.save(dish);
        log.info("Dish with id = {} successfully updated", id);
        return mapper.toDto(dish);
    }

    @Override
    public void delete(Long id) {
        Dish dish = getEntityById(id);
        repository.delete(dish);
        log.info("Dish with id = {} successfully deleted", id);
    }

    @Override
    public DishIngredientDto updateIngredient(Long dishId, Long ingredientId, UpdateDishIngredientDto dto) {
        return dishIngredientService.update(dishId, ingredientId, dto);
    }

    @Override
    public DishIngredientDto createIngredient(Long dishId, CreateDishIngredientDto dto) {
        Dish dish = getEntityById(dishId);
        Ingredient ingredient = ingredientService.getEntityById(dto.ingredientId());
        DishIngredient dishIngredient = dishIngredientService.create(dish, ingredient, dto);
        return dishIngredientService.toDto(dishIngredient);
    }

    @Override
    public void deleteIngredient(Long dishId, Long dishIngredientId) {
        dishIngredientService.delete(dishId, dishIngredientId);
        log.info("DishIngredient with id = {} successfully deleted", dishIngredientId);
    }

    private Dish getEntityById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Dish", id));
    }

    private void createDishIngredients(Dish dish, List<CreateDishIngredientDto> dtos) {
        List<DishIngredient> ingredients = new ArrayList<>();
        for (CreateDishIngredientDto ingredientDto : dtos) {
            Ingredient ingredient = ingredientService.getEntityById(ingredientDto.ingredientId());
            DishIngredient dishIngredient = dishIngredientService.create(dish, ingredient, ingredientDto);
            ingredients.add(dishIngredient);
        }
        dish.setIngredients(ingredients);
    }
}
