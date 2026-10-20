package oskar.rojek.s25723.masproject.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@ToString
@Table(name = "profesja", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"nazwa"})
})
public class Profesja {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotBlank(message = "Nazwa jest wymagana")
    @Size(min = 2, max = 50)
    @Column(nullable = false, unique = true)
    private String nazwa;
    @NotBlank(message = "Opis jest wymagany")
    @Size(min = 2, max = 1000)
    private String opis;

    @OneToMany(mappedBy = "profesja", fetch = FetchType.LAZY)
    @Builder.Default
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Praca> pracujacy = new HashSet<>();
}
