package ru.skubatko.dev.topjava.service.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "dishes")
@ToString(callSuper = true)
public class Dish extends NamedEntity implements HasId {

    public Dish(Dish d) {
        this(d.id, d.name);
    }

    public Dish(Integer id, String name) {
        super(id, name);
    }
}
