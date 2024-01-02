import {Component, signal} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {AngularFireAuth} from "@angular/fire/compat/auth";
import {AuthService} from "../../../shared/services/auth.service";
import {first} from "rxjs";
import {HttpResponse} from "@angular/common/http";
import {CookieManagerService} from "../../../shared/services/cookie-manager.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss'
})
export class LoginComponent {

  constructor(private authService:AuthService, private cookieManager:CookieManagerService, private router:Router) {
  }

  form = new FormGroup({
    email: new FormControl("", Validators.required),
    password: new FormControl("", Validators.required),
  })

  login() {
    this.authService.loginUser(
      this.form.get('email')?.value,
      this.form.get('password')?.value,
    ).pipe(first()).subscribe((data:HttpResponse<any>)=>{
    this.cookieManager.setToken(data.headers.get('Authorization')!);
    this.router.navigateByUrl('/admin/dashboard');
    },error => {
      console.log(error);
    })
  }

}
