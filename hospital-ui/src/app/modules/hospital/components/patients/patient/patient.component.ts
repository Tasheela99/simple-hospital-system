import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {PatientService} from "../../../../shared/services/patient.service";
import {finalize} from "rxjs";
import {MatSnackBar} from "@angular/material/snack-bar";

@Component({
  selector: 'app-patient',
  templateUrl: './patient.component.html',
  styleUrl: './patient.component.scss'
})
export class PatientComponent implements OnInit{
  ngOnInit() {
    this.getAllPatients();
  }

  displayedColumns: string[] = ['index', 'name', 'address', 'age', 'mobile', 'actions'];

  patients: object[] = [];

  selectedPatient: any;

  isUpdating = false;

  constructor(private patientService: PatientService, private snackBar:MatSnackBar) {
  }

  patientForm = new FormGroup({
    name: new FormControl("", Validators.required),
    address: new FormControl("", Validators.required),
    age: new FormControl("", Validators.required),
    mobile: new FormControl("", Validators.required),
  });


  createPatient(): void {
    this.patientService.createPatient(
      this.patientForm.get('name')?.value,
      this.patientForm.get('address')?.value,
      this.patientForm.get('age')?.value,
      this.patientForm.get('mobile')?.value,
    ).subscribe(response => {
      this.snackBar.open("Patient Created SuccessFully",'close')
      console.log(response)
    });
  }

  getAllPatients() {
    this.patientService.getAllPatients().subscribe(
      (response) => {
        console.log(response)
        this.patients = response.data;
      },
      (error) => {
        console.error('Error getting all patients', error);
      }
    );
  }

  editPatient(patient: any): void {
    this.selectedPatient = {...patient};
    this.patientForm.patchValue({
      name: this.selectedPatient.name,
      address: this.selectedPatient.address,
      age: this.selectedPatient.age,
      mobile: this.selectedPatient.mobile
    });
  }


  deletePatient(patientId: string): void {
    this.patientService.deletePatient(patientId).subscribe(
      (response) => {
        console.log('Patient deleted successfully:', response);
        this.snackBar.open("Patient Deleted SuccessFully",'close')

      },
      (error) => {
        console.error('Error deleting patient:', error);
      }
    );
  }

  updatePatient(): void {
    const {name, address, age, mobile} = this.patientForm.value;
    const id = this.selectedPatient.id;

    this.isUpdating = true;

    this.patientService
      .updatePatient(id, name, address, age, mobile)
      .pipe(finalize(() => (this.isUpdating = false)))
      .subscribe(
        (response) => {
          console.log('Patient updated successfully:', response);

        },
        (error) => {
          console.error('Error updating patient:', error);

        }
      );
  }


}
