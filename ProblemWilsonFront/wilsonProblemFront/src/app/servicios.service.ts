import { Injectable } from '@angular/core';
import { HttpClient, HttpParams, HttpHeaders } from '@angular/common/http';

@Injectable()
export class ServiciosService {

  constructor(private http: HttpClient) {
  }

  public enviarArchivos(cedula, archivo) {
    const formData: FormData = new FormData();
    const headers: HttpHeaders = new HttpHeaders();
    formData.append('file', archivo);
    headers.append('Content-type', 'form-data');
    return this.http.post
      ('localhost:8080/api/trabajos/analisis?cedula=' + cedula, formData, { headers, responseType: 'text' as 'json' });
  }
}
