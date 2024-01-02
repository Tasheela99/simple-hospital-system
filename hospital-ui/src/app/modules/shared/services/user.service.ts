import { Injectable } from '@angular/core';
import {Observable} from "rxjs";
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private apiUrl = 'http://localhost:8080/api/v1/user';

  constructor(private http: HttpClient) {
  }
  getAllUsers(): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}/get-all`);
  }
}
