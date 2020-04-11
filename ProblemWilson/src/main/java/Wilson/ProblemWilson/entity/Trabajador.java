package Wilson.ProblemWilson.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document("trabajador")
public class Trabajador {
    @Id
    private String cedula;
    //No va a ser necesario por el momento
    private String nombre;
    
}
