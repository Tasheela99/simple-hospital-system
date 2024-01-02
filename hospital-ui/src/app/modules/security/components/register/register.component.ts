import { Component } from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {AuthService} from "../../../shared/services/auth.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrl: './register.component.scss'
})
export class RegisterComponent {

  constructor(private authService:AuthService, private router:Router) {
  }

  form = new FormGroup({
    email: new FormControl("", Validators.required),
    firstName: new FormControl("", Validators.required),
    lastName: new FormControl("", Validators.required),
    password: new FormControl("", Validators.required),
  })

  register() {
    this.authService.createUser(
      this.form.get('email')?.value,
      this.form.get('firstName')?.value,
      this.form.get('lastName')?.value,
      this.form.get('password')?.value,
    ).subscribe(response=>{
      this.router.navigateByUrl("/security/login");
    });
  }

}
