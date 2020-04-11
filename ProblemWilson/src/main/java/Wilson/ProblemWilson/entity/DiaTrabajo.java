package Wilson.ProblemWilson.entity;

import java.util.ArrayList;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DiaTrabajo {
    //indicador de dia de trabajo (1,2,3... entre otros)
    private int dia;
    //elementos a llevar dicho dia
    private int elementos;
    //pesos de los elementos a llevar
    private ArrayList<Integer> pesos;
    //cantidad de viajes posibles segun el metodo Wilson (entre mas viajes mas pago)
    private int viajesDia;
}
