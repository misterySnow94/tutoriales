package Wilson.ProblemWilson.dto;

import java.util.ArrayList;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TrabajosDto {
    private Long id;
    //cantidad de dias de trabajo
    private int diasTrabajo;
    //fecha de registro en que se realiza el analisis
    private Date fechaRegistro;
    //Informacion del trabajador
    private TrabajadorDto trabajador;
    //dias de trabajo
    private ArrayList<DiaTrabajoDto> dias;
    
}
