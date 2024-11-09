package pe.edu.trentino.matricula.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "niveles_educativos")
public class NivelEducativo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;

    @OneToMany(mappedBy = "nivel", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Matricula> matriculas;

    @OneToMany(mappedBy = "nivel", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Grado> grados;
}
