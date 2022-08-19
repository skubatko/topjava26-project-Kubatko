package ru.skubatko.dev.topjava.service.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
@Table(name = "menu_items", uniqueConstraints = {@UniqueConstraint(columnNames = {"restaurant_id", "dish_id", "day"}, name = "menu_items_unique_restaurant_dish_day_idx")})
public class MenuItem extends BaseEntity implements HasId {

    @Column(name = "day", nullable = false)
    @NotNull
    private LocalDate day;

    @Column(name = "price", nullable = false)
    @Range(min = 100_00, max = 5000_00)
    private int price;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "dish_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private Dish dish;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private Restaurant restaurant;

    public MenuItem(MenuItem mi) {
        this(mi.id, mi.day, mi.price, mi.dish, mi.restaurant);
    }

    public MenuItem(Integer id, LocalDate day, int price, Dish dish, Restaurant restaurant) {
        super(id);
        this.day = day;
        this.price = price;
        this.dish = dish;
        this.restaurant = restaurant;
    }
}
