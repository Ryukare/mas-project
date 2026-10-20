package oskar.rojek.s25723.masproject.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Bron {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @NotBlank(message = "Nazwa jest wymagana")
    @Size(min = 2, max = 100)
    private String nazwa;
    @NotBlank(message = "Opis jest wymagany")
    @Size(min = 2, max = 1000)
    private String opis;
    @NotBlank(message = "Typ jest wymagany")
    @Size(min = 2, max = 100)
    private String typ;
    @NotBlank(message = "Rzadkość jest wymagana")
    @Size(min = 2, max = 100)
    private String rzadkosc;

    @Min(0)
    private int wymaganaSila;
    @Min(0)
    private int wymaganaZrecznosc;

    @Min(0)
    private int wymaganaInteligencja;

    @ManyToOne
    @JoinColumn(name = "id_wlasciciela")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Odkrywca wlasciciel;
}
