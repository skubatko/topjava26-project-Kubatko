package ru.skubatko.dev.topjava.service.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
@Table(name = "votes", uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "day"}, name = "votes_unique_user_day_idx")})
public class Vote extends BaseEntity implements HasId {

    @Column(name = "day", nullable = false)
    @NotNull
    private LocalDate day;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private Restaurant restaurant;

    public Vote(LocalDate day, User user, Restaurant restaurant) {
        this.day = day;
        this.user = user;
        this.restaurant = restaurant;
    }

    public Vote(Vote v) {
        this(v.id, v.day, v.user, v.restaurant);
    }

    public Vote(Integer id, LocalDate day, User user, Restaurant restaurant) {
        super(id);
        this.day = day;
        this.user = user;
        this.restaurant = restaurant;
    }
}
