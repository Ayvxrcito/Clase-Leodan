package pe.edu.trentino.matricula.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "alumnos")
public class Alumno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String dni;
    private String nombres;
    private String apellidos;
    @Column(unique = true)
    private String email;
    private String genero;
    private LocalDate fechaNac;

    @OneToMany(
            mappedBy = "alumno",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<Matricula> matriculas;
}
