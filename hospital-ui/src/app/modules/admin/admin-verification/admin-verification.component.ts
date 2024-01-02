import {Component, OnInit} from '@angular/core';
import {verify} from "node:crypto";

@Component({
  selector: 'app-admin-verification',
  templateUrl: './admin-verification.component.html',
  styleUrl: './admin-verification.component.scss'
})
export class AdminVerificationComponent implements OnInit{
 ngOnInit() {
   this.verify();
 }

 private verify(){

 }
}
