package eu.babych.winelibrary.model.wine;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.util.Set;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

@Entity
@Table(name = "wines")
@SQLRestriction("is_deleted = false")
@SQLDelete(sql = "UPDATE wines SET is_deleted = true WHERE id = ?")
@Getter
@Setter
public class Wine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(length = 1000)
    private String description;

    private String vintage;

    private Double alcohol;

    private String volume;

    private Long price;

    @Enumerated(EnumType.STRING)
    private WineType wineType;

    @Enumerated(EnumType.STRING)
    private SugarType sugarType;

    @Enumerated(EnumType.STRING)
    private AgingType agingType;

    @ManyToMany
    @JoinTable(name = "wine_foods",
            joinColumns = @JoinColumn(name = "wine_id"),
            inverseJoinColumns = @JoinColumn(name = "food_id"))
    private Set<Food> foods;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_id")
    private Country country;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id")
    private Region region;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "producer_id")
    private Producer producer;

    @ManyToMany
    @JoinTable(name = "wine_grapes",
            joinColumns = @JoinColumn(name = "wine_id"),
            inverseJoinColumns = @JoinColumn(name = "grape_id"))
    private Set<Grape> grapes;

    private String imageUrl;

    @Column(name = "is_deleted", nullable = false)
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private boolean deleted = false;
}
