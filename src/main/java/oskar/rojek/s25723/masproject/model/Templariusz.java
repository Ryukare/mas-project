package oskar.rojek.s25723.masproject.model;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@SuperBuilder
public class Templariusz extends Mag implements IWojownik{

    @Min(1)
    private int obrona;
    @NotBlank(message = "Preferowany typ broni jest wymagana")
    @Size(min = 2, max = 100)
    private String preferowanyTypBroni;

    @Override
    public int getPoziomMocy() {
        return (getDoswiadczenie() + getSila() + getZrecznosc() + getInteligencja() + obrona + getMana() + getZnaneCzary().size() * 10);
    }
}
