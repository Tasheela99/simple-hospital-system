import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class PaymentService {

  constructor(private http: HttpClient) {
  }


  findAllPatients() {
    return this.http.get<any>('http://localhost:8080/api/v1/patient/get-all');
  }

  getAllPatients(patientId: string) {
    return this.http.get<any>('http://localhost:8080/api/v1/patient/get-by-id/patientId=' + patientId);
  }

  getPatientDetails(patientId: string): Observable<any> {
    return this.http.get<any>(`http://localhost:8080/api/v1/patient/get-by-id`, { params: { patientId: patientId } });
  }

}
