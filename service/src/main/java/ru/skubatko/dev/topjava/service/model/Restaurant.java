package ru.skubatko.dev.topjava.service.model;

import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@NamedQueries({
        @NamedQuery(name = Restaurant.ALL_SORTED, query = "SELECT r FROM Restaurant r ORDER BY r.name"),
        @NamedQuery(name = Restaurant.UPDATE, query = "UPDATE Restaurant r SET r.name = :name where r.id=:id"),
        @NamedQuery(name = Restaurant.DELETE, query = "DELETE FROM Restaurant r WHERE r.id=:id")
})
@Entity
@NoArgsConstructor
@Table(name = "restaurants")
public class Restaurant extends NamedEntity {
    public static final String ALL_SORTED = "Restaurant.getAllSorted";
    public static final String UPDATE = "Restaurant.update";
    public static final String DELETE = "Restaurant.delete";

    @Override
    public String toString() {
        return "Restaurant{" +
                "id=" + id +
                ", name=" + name +
                '}';
    }
}
