import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';
import { Trabajos } from './models/Trabajos.models';
import { Observable } from 'rxjs';

@Injectable()
export class UtilidadesService {

  constructor(private http: HttpClient ) { }

  public enviarArchivo(cedula, archivo) {
    const formData: FormData = new FormData();
    const headers: HttpHeaders = new HttpHeaders();
    formData.append('archivo', archivo);
    headers.append('Content-type', 'form-data');
    return this.http.post
      ('http://localhost:8080/api/trabajos?cedula=' + cedula, formData, { headers, responseType: 'text' as 'json' });
  }

  public obtenerTrabajo(id: number): Observable<Trabajos> {
    return this.http.get<Trabajos>(
      'http://localhost:8080/api/trabajos?id=' + id);
  }

  public obtenerArchivo(id: number) {
    return this.http.get(
      'http://localhost:8080/api/trabajos/archivo?id=' + id, {responseType: 'text' as 'json'});
  }

}
