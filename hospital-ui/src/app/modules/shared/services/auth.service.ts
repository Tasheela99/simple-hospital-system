import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {map, Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http:HttpClient) { }

  public createUser(email:any,firstName:any,lastName:any,password:any):Observable<any>{

    return this.http.post('http://localhost:8080/api/v1/user/register',{
      email:email,
      firstName:firstName,
      lastName:lastName,
      password:password
    })
  }

  public loginUser(email:any,password:any):Observable<any>{
    return this.http.post<any>('http://localhost:8080/login',{
      username:email,
      password:password
    },{observe:'response' as 'body'}).pipe(map(data=>{
      return data;
    }))
  }

  isLoggedIn() {
    const token = localStorage.getItem('token');

    // Check if token is null or undefined
    if (!token) {
      return false;
    }

    try {
      const payload = atob(token.split('.')[1]);
      const parsedPayload = JSON.parse(payload);
      if (parsedPayload && parsedPayload.exp) {
        return parsedPayload.exp > Date.now() / 1000;
      } else {
        console.error('Token payload is missing expiration information.');
        return false;
      }
    } catch (error) {
      console.error('Error parsing token payload:', error);
      return false;
    }
  }


}
