import { Injectable } from '@angular/core';
import {CookieService} from "ngx-cookie-service";

@Injectable({
  providedIn: 'root'
})
export class CookieManagerService {

  constructor(private cookieService:CookieService) { }

  public setToken(token:string){
    this.cookieService.set('token',token);
  }
}
