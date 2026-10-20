package oskar.rojek.s25723.masproject.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Inheritance(strategy = InheritanceType.JOINED)
@SuperBuilder
public abstract class Odkrywca {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @NotBlank(message = "Nazwa jest wymagana")
    @Size(min = 2, max = 100)
    @Pattern(regexp = "^[A-Z][a-zA-Z'-]*$",
            message = "Nazwa musi zaczynać się wielką literą i nie może posiadać cyfr lub " +
                    "spedjalnych znaków po za myślnikiem i apostrofem")
    private String nazwa;
    @NotBlank(message = "Rasa jest wymagana")
    @Size(min = 2, max = 100)
    @Pattern(regexp = "^[A-Z][a-zA-Z'-]*$",
            message = "Rasa musi zaczynać się wielką literą i nie może posiadać cyfr lub " +
                    "spedjalnych znaków po za myślnikiem i apostrofem")
    private String rasa;
    @NotBlank(message = "Miejsce urodzenia jest wymagane")
    @Size(min = 2, max = 100)
    @Pattern(regexp = "^[A-Z][a-zA-Z'-]*$",
            message = "Miejsce urodzenia  musi zaczynać się wielką literą i nie może posiadać cyfr lub " +
                    "spedjalnych znaków po za myślnikiem i apostrofem")
    private String miejsceUrodzenia;

    @Min(1)
    private int sila;
    @Min(1)
    private int zrecznosc;
    @Min(1)
    private int inteligencja;
    @Min(0)
    private int doswiadczenie;

    public void setDoswiadczenie(int doswiadczenie) {
        if(doswiadczenie < this.doswiadczenie){
            throw new IllegalArgumentException();
        }
        this.doswiadczenie = doswiadczenie;
    }

    public abstract int getPoziomMocy();

    @ManyToMany(cascade = {CascadeType.REMOVE}, fetch = FetchType.EAGER)
    @JoinTable(
            name = "odkrywca_ekspedycja",
            joinColumns = @JoinColumn(name = "id_odkrywcy"),
            inverseJoinColumns = @JoinColumn(name = "id_ekspedycji")
    )
    @Builder.Default
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Ekspedycja> ekspedycje = new HashSet<>();

    @OneToMany(mappedBy = "odkrywca", fetch = FetchType.LAZY)
    @Builder.Default
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Praca> prace = new HashSet<>();

    @OneToMany(mappedBy = "wlasciciel", fetch = FetchType.EAGER)
    @Builder.Default
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Bron> bronie = new HashSet<>();
}
