import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdminComponent } from './admin.component';
import {AdminVerificationComponent} from "./admin-verification/admin-verification.component";
import {AllAdminsComponent} from "./all-admins/all-admins.component";
import {NewDoctorComponent} from "./new-doctor/new-doctor.component";
import {DashboardComponent} from "./dashboard/dashboard.component";

const routes: Routes = [{ path: '', component: AdminComponent,children:[
    {path:'admin-verification',component:AdminVerificationComponent},
    {path:'all-admin',component:AllAdminsComponent},
    {path:'new-doctor',component:NewDoctorComponent},
    {path:'dashboard',component:DashboardComponent},
  ] }];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminRoutingModule { }
