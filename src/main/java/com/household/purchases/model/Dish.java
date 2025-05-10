package com.household.purchases.model;

import com.household.purchases.enums.WeatherType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "dishes")
public class Dish {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    @Column(name = "weather_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private WeatherType weatherType = WeatherType.ANY;

    @OneToMany(mappedBy = "dish")
    private List<DishIngredient> ingredients = new ArrayList<>();
}
