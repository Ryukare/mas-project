package oskar.rojek.s25723.masproject.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@ToString
@Table(name = "starozytnaRuina", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"nazwa"})
})
public class StarozytnaRuina {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank(message = "Nazwa jest wymagana")
    @Size(min = 2, max = 50)
    @Column(nullable = false, unique = true)
    private String nazwa;
    @Min(0)
    private int wiek;
    @Min(1)
    private int poziomTrudnosci;
    @Min(1)
    private int wymaganaMoc;
    @OneToMany(mappedBy = "ruina", cascade = {CascadeType.REMOVE}, fetch = FetchType.EAGER)
    @Builder.Default
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Ekspedycja> ekspedycje = new HashSet<>();
    @ManyToOne
    @JoinColumn(name = "kraina_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Kraina kraina;

    @OneToMany(mappedBy = "ruina", cascade = {CascadeType.REMOVE}, fetch = FetchType.LAZY)
    @Builder.Default
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Starozytny> starozytni = new HashSet<>();
}
