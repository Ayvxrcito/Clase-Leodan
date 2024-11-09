package pe.edu.trentino.matricula.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "apoderados")
public class Apoderado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String dni;
    private String nombres;
    private String apellidos;
    @Column(unique = true)
    private String email;
    private String telefono;
    @JsonIgnore
    private LocalDateTime createdAt;

    @OneToMany(
            mappedBy = "apoderado",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<Matricula> matriculas;
}
