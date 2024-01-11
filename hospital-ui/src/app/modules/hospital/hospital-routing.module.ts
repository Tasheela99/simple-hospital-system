import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HospitalComponent } from './hospital.component';
import {AddDoctorComponent} from "./components/doctors/add-doctor/add-doctor.component";
import {PatientComponent} from "./components/patients/patient/patient.component";
import {InvenoryHolderComponent} from "./components/holders/invenory-holder/invenory-holder.component";
import {
  MedicineInventoryComponent
} from "./components/medicine-inventory/medicine-inventory/medicine-inventory.component";
import {MedicineComponent} from "./components/medicine/medicine.component";
import {PaymentComponent} from "./components/payment/payment.component";

const routes: Routes = [{ path: '', component: HospitalComponent,children:[
    {path:'doctor',component:AddDoctorComponent},
    {path:'patient',component:PatientComponent},
    {path:'holder',component:InvenoryHolderComponent},
    {path:'inventory',component:MedicineInventoryComponent},
    {path:'medicine',component:MedicineComponent},
    {path:'payment',component:PaymentComponent},
  ] }];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class HospitalRoutingModule { }
