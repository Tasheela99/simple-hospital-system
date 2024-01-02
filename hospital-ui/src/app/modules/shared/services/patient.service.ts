import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class PatientService {


  private apiUrl = 'http://localhost:8080/api/v1/patient';

  constructor(private http: HttpClient) {
  }

  createPatient(name: any, address: any, age: any, mobile: any): Observable<any> {
    return this.http.post('http://localhost:8080/api/v1/patient/create', {
      name: name,
      address: address,
      age: age,
      mobile: mobile
    })
  }



  updatePatient(id: string, name: any, address: any, age: any, mobile: any): Observable<any> {
    return this.http.put(`${this.apiUrl}/update/${id}`, {
      name: name,
      address: address,
      age: age,
      mobile: mobile
    });
  }

  getAllPatients(): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}/get-all`);
  }


  deletePatient(id: string): Observable<any> {
    return this.http.delete(`${this.apiUrl}/delete`, {params: {id}});
  }

  count(): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}/count`);
  }
}
