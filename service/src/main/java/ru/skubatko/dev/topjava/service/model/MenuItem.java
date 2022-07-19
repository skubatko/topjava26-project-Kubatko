package ru.skubatko.dev.topjava.service.model;

import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@NamedQueries({
        @NamedQuery(name = MenuItem.ALL_SORTED, query = "SELECT mi FROM MenuItem mi WHERE mi.restaurant.id=:restaurantId ORDER BY mi.day DESC"),
        @NamedQuery(name = MenuItem.DELETE, query = "DELETE FROM MenuItem mi WHERE mi.id=:id AND mi.restaurant.id=:restaurantId"),
        @NamedQuery(name = MenuItem.UPDATE, query = "UPDATE MenuItem mi SET mi.day = :day where mi.id=:id and mi.restaurant.id=:restaurantId")
})
@Entity
@NoArgsConstructor
@Table(name = "menu_items")
//@Table(name = "menu_items", uniqueConstraints = {@UniqueConstraint(columnNames = {"restaurant_id", "day", "dish_id"}, name = "menu_items_unique_restaurant_day_dish_idx")})
public class MenuItem extends NamedEntity {
    public static final String ALL_SORTED = "MenuItem.getAll";
    public static final String DELETE = "MenuItem.delete";
    public static final String UPDATE = "MenuItem.update";

    @Column(name = "day", nullable = false)
    @NotNull
    private Date day;

    @Column(name = "price", nullable = false)
    @Range(min = 500, max = 5000)
    private int price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dish_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private Dish dish;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private Restaurant restaurant;

    public MenuItem(Integer id, String name, Date day, int price) {
        super(id, name);
        this.day = day;
        this.price = price;
    }

    @Override
    public String toString() {
        return "MenuItem{" +
                "id=" + id +
                ", name='" + name +
                ", day=" + day +
                ", price=" + price +
                "}";
    }
}
