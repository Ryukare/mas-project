package oskar.rojek.s25723.masproject.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Min;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.HashSet;
import java.util.Set;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@SuperBuilder
public class Zwiadowca extends Odkrywca {
    @Min(1)
    private int celnosc;
    @Min(1)
    private int poziomUmiejetnosciZwiadowczych;

    @Override
    public int getPoziomMocy() {
        return (int) (getDoswiadczenie() + getSila() * 0.5 + getZrecznosc() + getInteligencja() * 0.5 + celnosc + poziomUmiejetnosciZwiadowczych);
    }

    @OneToMany(mappedBy = "zwiadowca", cascade = {CascadeType.REMOVE}, fetch = FetchType.LAZY)
    @Builder.Default
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<RozpoznanieWTerenie> wiedzaOKrainach = new HashSet<>();
}
