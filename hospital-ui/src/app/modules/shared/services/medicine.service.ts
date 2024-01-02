import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {catchError, Observable, tap, throwError} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class MedicineService {

  private apiUrl = 'http://localhost:8080/api/v1/medicine';

  constructor(private http: HttpClient) {
  }

  createMedicine(inventoryId: string | null | undefined, name: any, type: any, qty: any, unitPrice: any): Observable<any> {
    const url = `${this.apiUrl}/create?inventoryId=${inventoryId}`;
    return this.http.post<any>(url, {
      name: name,
      type: type,
      qty: qty,
      unitPrice: unitPrice,
    });
  }

  getAllMedicines(): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}/get-all`);
  }

  deleteMedicine(id: string): Observable<any> {
    return this.http.delete(`${this.apiUrl}/delete`, {params: {id}});
  }

  count(): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}/count`).pipe(
      tap(data => console.log('Count data:', data)),
      catchError(error => {
        console.error('Error fetching count:', error);
        return throwError(error);
      })
    );
  }
}
