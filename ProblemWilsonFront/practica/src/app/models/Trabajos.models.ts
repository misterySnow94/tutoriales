import { Trabajador } from './Trabajador.models';
import { DiaTrabajo } from './DiaTrabajo.models';

export interface Trabajos {
    diasTrabajo: number;
    fechaRegistro: Date;
    trabajador: Trabajador;
    dias: Array<DiaTrabajo>;
}
