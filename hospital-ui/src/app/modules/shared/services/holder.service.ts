import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class HolderService {

  private apiUrl = 'http://localhost:8080/api/v1/medicine-inventory-holder';


  constructor(private http:HttpClient) { }
  createHolder(name:any): Observable<any> {
    return this.http.post('http://localhost:8080/api/v1/medicine-inventory-holder/create',{
      name:name,
    })
  }

  getAllHolders(): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}/get-all`);
  }


  deleteHolder(id: string): Observable<any> {
    return this.http.delete(`${this.apiUrl}/delete`, {params: {id}});
  }

  count(): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}/count`);
  }

  getAllMedicalHolderInventoryIds(): Observable<any> {
    const url = `${this.apiUrl}/get-all-ids`;
    return this.http.get(url);
  }
}
