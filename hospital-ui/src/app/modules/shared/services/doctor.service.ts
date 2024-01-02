import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {RequestDoctorDto} from "../dtos/request-doctor";

@Injectable({
  providedIn: 'root'
})
export class DoctorService {


  private apiUrl = 'http://localhost:8080/api/v1/doctor';

  constructor(private http: HttpClient) {
  }

  createDoctor(name: any, address: any, mobile: any, speciality: any): Observable<any> {
    return this.http.post('http://localhost:8080/api/v1/doctor/create', {
      name: name,
      address: address,
      mobile: mobile,
      speciality: speciality
    })
  }

  getAllDoctors(): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}/get-all`);
  }

  updateDoctor(id: string, name: any, address: any, mobile: any, speciality: any): Observable<any> {
    return this.http.put(`${this.apiUrl}/update/${id}`, {
      name: name,
      address: address,
      mobile: mobile,
      speciality: speciality
    });
  }


  deleteDoctor(id: string): Observable<any> {
    return this.http.delete(`${this.apiUrl}/delete`, {params: {id}});
  }

  count(): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}/count`);
  }
}
