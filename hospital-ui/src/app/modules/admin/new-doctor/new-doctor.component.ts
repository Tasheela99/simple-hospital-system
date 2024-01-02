import {Component, OnInit} from '@angular/core';
import {Route, Router} from "@angular/router";

@Component({
  selector: 'app-new-doctor',
  templateUrl: './new-doctor.component.html',
  styleUrl: './new-doctor.component.scss'
})
export class NewDoctorComponent implements OnInit{

  constructor(private router:Router) {
  }
  ngOnInit() {
    this.router.navigateByUrl('/admin/admin-verification ')
  }
}
