package ru.skubatko.dev.topjava.service.model;

import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.validator.constraints.Range;
import ru.skubatko.dev.topjava.service.HasId;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@NamedQueries({
        @NamedQuery(name = MenuItem.ALL_SORTED, query = "SELECT mi FROM MenuItem mi WHERE mi.restaurant.id=:restaurantId ORDER BY mi.day DESC"),
        @NamedQuery(name = MenuItem.DELETE, query = "DELETE FROM MenuItem mi WHERE mi.id=:id AND mi.restaurant.id=:restaurantId"),
        @NamedQuery(name = MenuItem.UPDATE, query = "UPDATE MenuItem mi SET mi.day = :day where mi.id=:id and mi.restaurant.id=:restaurantId")
})
@Entity
@Table(name = "menu_items", uniqueConstraints = {@UniqueConstraint(columnNames = {"restaurant_id", "dish_id", "day"}, name = "menu_items_unique_restaurant_dish_day_idx")})
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(callSuper = true)
public class MenuItem extends BaseEntity implements HasId {
    public static final String ALL_SORTED = "MenuItem.getAll";
    public static final String DELETE = "MenuItem.delete";
    public static final String UPDATE = "MenuItem.update";

    @Column(name = "day", nullable = false)
    @NotNull
    private Date day;

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

    public MenuItem(Integer id, Date day, int price, Dish dish, Restaurant restaurant) {
        super(id);
        this.day = day;
        this.price = price;
        this.dish = dish;
        this.restaurant = restaurant;
    }
}
