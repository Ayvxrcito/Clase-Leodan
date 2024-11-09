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
@Table(name = "bancos")
public class Banco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, unique = true, nullable = false)
    private String nombre;
    @Column(length = 100, nullable = false)
    private String direccion;
    @Column(length = 20, nullable = false)
    private String codigo;

    @OneToMany(mappedBy = "banco")
    private List<Pago> pagos;
}
