package ru.skubatko.dev.topjava.service.model;

import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import ru.skubatko.dev.topjava.service.HasId;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@NamedQueries({
        @NamedQuery(name = Vote.ALL_SORTED, query = "SELECT v FROM Vote v WHERE v.user.id=:userId ORDER BY v.day DESC"),
        @NamedQuery(name = Vote.DELETE, query = "DELETE FROM Vote v WHERE v.id=:id AND v.user.id=:userId"),
        @NamedQuery(name = Vote.UPDATE, query = "UPDATE Vote v SET v.day=:day where v.id=:id and v.user.id=:userId")})
@Entity
@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "votes", uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "day"}, name = "votes_unique_user_day_idx")})
public class Vote extends BaseEntity implements HasId {
    public static final String ALL_SORTED = "Vote.getAll";
    public static final String DELETE = "Vote.delete";
    public static final String UPDATE = "Vote.update";

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
