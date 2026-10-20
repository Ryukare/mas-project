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
@Table(name = "kraina", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"nazwa"})
})
public class Kraina {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank(message = "Nazwa jest wymagana")
    @Size(min = 2, max = 50)
    @Column(nullable = false, unique = true)
    private String nazwa;
    @NotBlank(message = "Dominujący żywioł jest wymagany")
    @Size(min = 2, max = 50)
    private String dominujacyZywiol;
    @Min(1)
    private int wielkosc;
    @Min(0)
    private int populacja;
    @OneToMany(mappedBy = "kraina", fetch = FetchType.EAGER)
    @Builder.Default
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @Size(min = 1, message = "Kraina musi posiadać min. 1 ruinę")
    private Set<StarozytnaRuina> ruiny = new HashSet<>();

    @OneToMany(mappedBy = "kraina", cascade = {CascadeType.REMOVE}, fetch = FetchType.LAZY)
    @Builder.Default
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<RozpoznanieWTerenie> wiedzaOTerenach = new HashSet<>();

}
