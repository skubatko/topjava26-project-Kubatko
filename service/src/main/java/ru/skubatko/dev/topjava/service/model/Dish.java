package ru.skubatko.dev.topjava.service.model;

import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@NamedQueries({
        @NamedQuery(name = Dish.ALL_SORTED, query = "SELECT d FROM Dish d ORDER BY d.name ASC"),
        @NamedQuery(name = Dish.UPDATE, query = "UPDATE Dish d SET d.name = :name where d.id=:id"),
        @NamedQuery(name = Dish.DELETE, query = "DELETE FROM Dish d WHERE d.id=:id")
})
@Entity
@NoArgsConstructor
@Table(name = "dishes")
public class Dish extends NamedEntity {
    public static final String ALL_SORTED = "Dish.getAll";
    public static final String UPDATE = "Dish.update";
    public static final String DELETE = "Dish.delete";

    public Dish(Integer id, String name) {
        super(id, name);
    }

    @Override
    public String toString() {
        return "Dish{" +
                "id=" + id +
                ", name='" + name +
                "}";
    }
}
