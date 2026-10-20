package oskar.rojek.s25723.masproject.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@ToString
public class Ekspedycja {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Nazwa jest obowiązkowa")
    @Size(min = 2, max = 50)
    private String nazwa;
    @NotNull
    private LocalDate dataRozpoczecia;
    private LocalDate dataZakonczenia;

    @Enumerated(EnumType.STRING)
    private StatusEkspedycji status;
    @ManyToOne(optional = false)
    @JoinColumn(name = "ruina_id", nullable = false, updatable = false)
    @NotNull
    private StarozytnaRuina ruina;

    @ManyToMany(cascade = {CascadeType.REMOVE}, fetch = FetchType.LAZY)
    @JoinTable(
            name = "odkrywca_ekspedycja",
            joinColumns = @JoinColumn(name = "id_ekspedycji"),
            inverseJoinColumns = @JoinColumn(name = "id_odkrywcy")
    )
    @Builder.Default
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @Size(min = 1, message = "Ekspedycja musi mieć min. 1 odkrywcę")
    private Set<Odkrywca> druzyna = new HashSet<>();
}
