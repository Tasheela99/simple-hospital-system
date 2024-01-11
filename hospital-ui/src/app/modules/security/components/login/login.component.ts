import {Component, signal} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {AuthService} from "../../../shared/services/auth.service";
import {first} from "rxjs";
import {HttpResponse} from "@angular/common/http";
import {CookieManagerService} from "../../../shared/services/cookie-manager.service";
import {Router} from "@angular/router";
import {MatSnackBar} from "@angular/material/snack-bar";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss'
})
export class LoginComponent {

  constructor(private authService:AuthService, private cookieManager:CookieManagerService, private router:Router, private snackBar:MatSnackBar) {
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
      this.snackBar.open("Welcome",'close',{
        duration:5000,
        verticalPosition:"top",
        horizontalPosition:"right"
      })
    this.cookieManager.setToken(data.headers.get('Authorization')!);
    this.router.navigateByUrl('/admin/dashboard');
    },error => {
      this.snackBar.open("Login Failed Please Try Again",'close',{
        duration:2000,
        verticalPosition:"top",
        horizontalPosition:"right",
      })
      console.log(error);
    })
  }

}
