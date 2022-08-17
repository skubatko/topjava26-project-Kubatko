package ru.skubatko.dev.topjava.service.model;

import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import ru.skubatko.dev.topjava.service.HasId;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "votes", uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "day"}, name = "votes_unique_user_day_idx")})
public class Vote extends BaseEntity implements HasId {
    public Vote(User user, Restaurant restaurant) {
        this.day = new Date();
        this.user = user;
        this.restaurant = restaurant;
    }

    @Column(name = "day", nullable = false)
    @NotNull
    private Date day;

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

    public Vote(Vote v) {
        this(v.id, v.day, v.user, v.restaurant);
    }

    public Vote(Integer id, Date day, User user, Restaurant restaurant) {
        super(id);
        this.day = day;
        this.user = user;
        this.restaurant = restaurant;
    }
}
