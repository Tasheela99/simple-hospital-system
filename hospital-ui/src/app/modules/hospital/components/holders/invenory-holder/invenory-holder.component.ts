import {Component, OnInit, signal} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {HolderService} from "../../../../shared/services/holder.service";

@Component({
  selector: 'app-invenory-holder',
  templateUrl: './invenory-holder.component.html',
  styleUrl: './invenory-holder.component.scss'
})
export class InvenoryHolderComponent implements OnInit{
  ngOnInit() {
    this.getAllHolders();
  }

  displayedColumns: string[] = ['index', 'id', 'name','actions'];

  holders: object[] = [];

  constructor(private holderService: HolderService) {
  }

  holderForm = new FormGroup({
    name: new FormControl("", Validators.required),
  });

  createHolder() {
    this.holderService.createHolder(
      this.holderForm.get('name')?.value,
    ).subscribe(response=>{
      console.log(response)
    });
  }

  getAllHolders() {
    this.holderService.getAllHolders().subscribe(
      (response) => {
        console.log(response)
        this.holders = response.data;
      },
      (error) => {
        console.error('Error getting all Holders', error);
      }
    );
  }



  deleteHolder(holderId: string): void {
    this.holderService.deleteHolder(holderId).subscribe(
      (response) => {
        console.log('Patient deleted successfully:', response);
      },
      (error) => {
        console.error('Error deleting patient:', error);
      }
    );
  }

}
