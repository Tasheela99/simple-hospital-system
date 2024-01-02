import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { HospitalRoutingModule } from './hospital-routing.module';
import { HospitalComponent } from './hospital.component';
import { AddDoctorComponent } from './components/doctors/add-doctor/add-doctor.component';
import { UpdateDoctorComponent } from './components/doctors/update-doctor/update-doctor.component';
import {MatInputModule} from "@angular/material/input";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {MatButtonModule} from "@angular/material/button";
import { PatientComponent } from './components/patients/patient/patient.component';
import { InvenoryHolderComponent } from './components/holders/invenory-holder/invenory-holder.component';
import { MedicineInventoryComponent } from './components/medicine-inventory/medicine-inventory/medicine-inventory.component';
import {MatDatepickerModule} from "@angular/material/datepicker";
import {MatSelectModule} from "@angular/material/select";
import {MatTableModule} from "@angular/material/table";
import {MatIconModule} from "@angular/material/icon";
import {MatToolbarModule} from "@angular/material/toolbar";
import { MedicineComponent } from './components/medicine/medicine.component';
import {MatAutocompleteModule} from "@angular/material/autocomplete";


@NgModule({
  declarations: [
    HospitalComponent,
    AddDoctorComponent,
    UpdateDoctorComponent,
    PatientComponent,
    InvenoryHolderComponent,
    MedicineInventoryComponent,
    MedicineComponent
  ],
    imports: [
        CommonModule,
        HospitalRoutingModule,
        MatInputModule,
        ReactiveFormsModule,
        MatButtonModule,
        MatDatepickerModule,
        MatSelectModule,
        MatTableModule,
        MatIconModule,
        MatToolbarModule,
        FormsModule,
        MatAutocompleteModule
    ]
})
export class HospitalModule { }
