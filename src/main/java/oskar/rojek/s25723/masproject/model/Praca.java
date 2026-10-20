package oskar.rojek.s25723.masproject.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@ToString
public class Praca {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull
    private LocalDate dataRozpoczecia;
    private LocalDate dataZakonczenia;
    @Min(1)
    private int ekspertyza;
    private static int placaMinimalna = 1000;

    public static int getPlacaMinimalna() {
        return placaMinimalna;
    }

    private int placa;
    @ManyToOne
    @JoinColumn(name = "id_odkrywcy", nullable = false)
    @NotNull
    private Odkrywca odkrywca;
    @ManyToOne
    @JoinColumn(name = "id_profesji", nullable = false)
    @NotNull
    private Profesja profesja;
}
