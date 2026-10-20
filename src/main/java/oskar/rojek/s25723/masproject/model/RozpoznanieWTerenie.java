package oskar.rojek.s25723.masproject.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@ToString
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"id_zwiadowcy", "id_krainy"})})
public class RozpoznanieWTerenie {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Min(0)
    @Max(1)
    private double procentRozpoznania;
    @ManyToOne
    @JoinColumn(name = "id_zwiadowcy", nullable = false)
    @NotNull
    private Zwiadowca zwiadowca;
    @ManyToOne
    @JoinColumn(name = "id_krainy", nullable = false)
    @NotNull
    private Kraina kraina;
}
