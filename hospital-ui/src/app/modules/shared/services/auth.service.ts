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

}
