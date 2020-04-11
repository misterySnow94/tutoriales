import { Component, ViewEncapsulation } from '@angular/core';
import {UtilidadesService} from './utilidades.service';
import { Trabajos } from './models/Trabajos.models';
import {trigger, state, style, transition, animate, AnimationEvent} from '@angular/animations';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  providers: [UtilidadesService],
  animations: [
    trigger('animation', [
        state('visible', style({
            transform: 'translateX(0)',
            opacity: 1
        })),
        transition('void => *', [
            style({transform: 'translateX(50%)', opacity: 0}),
            animate('300ms ease-out')
        ]),
        transition('* => void', [
            animate(('250ms ease-in'), style({
                height: 0,
                opacity: 0,
                transform: 'translateX(50%)'
            }))
        ])
    ])
  ],
  encapsulation: ViewEncapsulation.None
})
export class AppComponent {
  title = 'practica';
  fileToUpload: File = null;
  cedula = '';
  id: any;
  trabajo: Trabajos = null;
  url : any;
  cols: any[];

  constructor(private utilidad: UtilidadesService) {

  }

  public handleFileInput(files: FileList) {
    this.fileToUpload = files.item(0);
  }

  public enviarArchivo() {
    this.utilidad.enviarArchivo(this.cedula, this.fileToUpload)
      .subscribe(resultado => {
        this.id = resultado;
      });
    setTimeout(() => {
      this.cargarTrabajo(this.id);
    }, 1000);
  }

  public cargarTrabajo(id: number) {
    this.utilidad.obtenerTrabajo(id)
      .subscribe(resultado => {
        this.trabajo = <Trabajos> resultado;
      });
  }

  public descargarArchivo() {
    this.utilidad.obtenerArchivo(this.id)
    .subscribe(resultado => {
      window.open(<string> resultado);
    });
  }

}
