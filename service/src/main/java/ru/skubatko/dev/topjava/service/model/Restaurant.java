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
@ToString(callSuper = true)
@Table(name = "restaurants")
public class Restaurant extends NamedEntity implements HasId {

    public Restaurant(Restaurant r) {
        this(r.id, r.name);
    }

    public Restaurant(Integer id, String name) {
        super(id, name);
    }
}
