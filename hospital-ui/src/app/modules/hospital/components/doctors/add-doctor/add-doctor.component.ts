import {ChangeDetectionStrategy, ChangeDetectorRef, Component, OnInit} from '@angular/core';
import {DoctorService} from "../../../../shared/services/doctor.service";
import {create} from "node:domain";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {response} from "express";
import {MatTableDataSource} from "@angular/material/table";
import {RequestDoctorDto} from "../../../../shared/dtos/request-doctor";
import {finalize} from "rxjs";

@Component({
  selector: 'app-add-doctor',
  templateUrl: './add-doctor.component.html',
  styleUrl: './add-doctor.component.scss',
  changeDetection: ChangeDetectionStrategy.Default
})
export class AddDoctorComponent implements OnInit{
  displayedColumns: string[] = ['index','name', 'address', 'mobile','speciality','actions'];

  doctors: object[] = [];

  selectedDoctor: any;

  isUpdating = false;


  ngOnInit() {
   this.getAllDoctors()
  }

  constructor(private doctorService: DoctorService, private cdr: ChangeDetectorRef) {}

  doctorForm= new FormGroup({
    name:new FormControl("",Validators.required),
    address:new FormControl("",Validators.required),
    mobile:new FormControl("",Validators.required),
    speciality:new FormControl("",Validators.required),
  });


  createDoctor(): void {
   this.doctorService.createDoctor(
     this.doctorForm.get('name')?.value,
     this.doctorForm.get('address')?.value,
     this.doctorForm.get('mobile')?.value,
     this.doctorForm.get('speciality')?.value,
   ).subscribe(response=>{
     console.log(response)
   });
  }

  getAllDoctors() {
    this.doctorService.getAllDoctors().subscribe(
      (response) => {
        console.log(response)
        this.doctors = response.data;
      },
      (error) => {
        console.error('Error getting all doctors', error);
      }
    );
  }

  editDoctor(doctor: any): void {
    this.selectedDoctor = { ...doctor };
    this.doctorForm.patchValue({
      name: this.selectedDoctor.name,
      address: this.selectedDoctor.address,
      mobile: this.selectedDoctor.mobile,
      speciality: this.selectedDoctor.speciality,
    });
  }



  deleteDoctor(doctorId: string): void {
    this.doctorService.deleteDoctor(doctorId).subscribe(
      (response) => {
        console.log('Doctor deleted successfully:', response);
      },
      (error) => {
        console.error('Error deleting doctor:', error);
      }
    );
  }

  updateDoctor(): void {
    const { name, address, mobile, speciality } = this.doctorForm.value;
    const id = this.selectedDoctor.id;

    this.isUpdating = true;

    this.doctorService
      .updateDoctor(id, name, address, mobile, speciality)
      .pipe(finalize(() => (this.isUpdating = false)))
      .subscribe(
        (response) => {
          console.log('Doctor updated successfully:', response);

        },
        (error) => {
          console.error('Error updating doctor:', error);

        }
      );
  }





}
