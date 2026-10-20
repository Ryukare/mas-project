package oskar.rojek.s25723.masproject.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
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
public class Mag extends Odkrywca{
    @Min(1)
    private int mana;
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "czar_maga", joinColumns = @JoinColumn(name = "id_maga"))
    @Builder.Default
    @Size(min = 1, message = "Mag musi znać min. 1 czar")
    private Set<String> znaneCzary = new HashSet<>();
    @NotBlank(message = "Nazwa akademii jest wymagana")
    @Size(min = 2, max = 100)
    private String nazwaAkademii;

    @Override
    public int getPoziomMocy() {
        return (int) (getDoswiadczenie() + getSila() * 0.25 + getZrecznosc() * 0.75 + getInteligencja() + mana + znaneCzary.size() * 10);
    }
}
