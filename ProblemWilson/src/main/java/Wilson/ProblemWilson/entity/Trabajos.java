package Wilson.ProblemWilson.entity;

import java.util.ArrayList;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document("trabajos")
public class Trabajos {
    @Transient
    public static final String SEQUENCE_NAME = "trabajos_sequence";
    @Id
    private long id;
    //cantidad de dias de trabajo
    private int diasTrabajo;
    //fecha de registro en que se realiza el analisis
    private Date fechaRegistro;
    //Informacion del trabajador
    private Trabajador trabajador;
    //dias de trabajo
    private ArrayList<DiaTrabajo> dias;
    
}
