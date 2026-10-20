package oskar.rojek.s25723.masproject.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class PowietrznyStarozytny {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Min(1)
    private int rozmiarSkrzydel;
    @Min(1)
    private int predkoscLotu;

    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "id_starozytnego", referencedColumnName = "id")
    @NotNull
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Starozytny starozytny;
}
