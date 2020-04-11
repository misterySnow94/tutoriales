import { Component, OnInit } from '@angular/core';
import {ServiciosService} from './servicios.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  providers: [ServiciosService]
})

export class AppComponent implements OnInit {
  fileToUpload: File = null;
  cedula: string = '';

  constructor(private servicios: ServiciosService){}

  ngOnInit() {
  }

  handleFileInput(files: FileList) {
    this.fileToUpload = files.item(0);
    console.log(this.fileToUpload);
  }

  enviarInformacion(){
    console.log('cedula:' + this.cedula);
    console.log(this.fileToUpload);
    this.servicios.enviarArchivos(this.cedula, this.fileToUpload);
  }
}
