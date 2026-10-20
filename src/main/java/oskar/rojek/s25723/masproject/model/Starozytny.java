package oskar.rojek.s25723.masproject.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Starozytny {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @NotBlank(message = "Nazwa jest wymagana")
    @Size(min = 2, max = 100)
    private String nazwa;
    @NotBlank(message = "Opis jest wymagany")
    @Size(min = 2, max = 1000)
    private String opis;
    @Min(100)
    private int zycie;
    @Min(1)
    private int sila;
    @Min(1)
    private int odpornosc;
    @ElementCollection
    @CollectionTable(name = "umiejetnosc_starozytnego", joinColumns = @JoinColumn(name = "id_starozytnego"))
    @Builder.Default
    @Size(min = 1, message = "Starożytny musi mieć min. 1 umiejętność")
    private Set<String> umiejetnosci = new HashSet<>();

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_ruiny", nullable = false, updatable = false)
    @NotNull
    private StarozytnaRuina ruina;

    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "id_przekletego", referencedColumnName = "id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private PrzekletyStarozytny przekletyStarozytny;
    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "id_powietrznego", referencedColumnName = "id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private PowietrznyStarozytny powietrznyStarozytny;
}
