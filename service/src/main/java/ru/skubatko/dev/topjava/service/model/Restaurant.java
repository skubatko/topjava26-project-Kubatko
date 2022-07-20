package ru.skubatko.dev.topjava.service.model;

import lombok.*;
import ru.skubatko.dev.topjava.service.HasId;

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
@Getter
@Setter
@Table(name = "restaurants")
@ToString(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Restaurant extends NamedEntity implements HasId {
    public static final String ALL_SORTED = "Restaurant.getAllSorted";
    public static final String UPDATE = "Restaurant.update";
    public static final String DELETE = "Restaurant.delete";

    public Restaurant(Restaurant r) {
        this(r.id, r.name);
    }

    public Restaurant(Integer id, String name) {
        super(id, name);
    }
}
