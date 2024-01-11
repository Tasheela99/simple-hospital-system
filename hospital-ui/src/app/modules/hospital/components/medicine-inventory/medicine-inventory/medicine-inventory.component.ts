import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {InventoryService} from "../../../../shared/services/inventory.service";
import {HolderService} from "../../../../shared/services/holder.service";
import {MatSnackBar} from "@angular/material/snack-bar";

@Component({
  selector: 'app-medicine-inventory',
  templateUrl: './medicine-inventory.component.html',
  styleUrl: './medicine-inventory.component.scss'
})
export class MedicineInventoryComponent implements OnInit{
  ngOnInit() {
    this.getAllInventories();
    this.getAllHolderIds();
  }

  displayedColumns: string[] = ['index','holderId', 'name', 'qty','unitPrice','manufacture','expire','actions'];

  inventories: object[] = [];

  holders: String[] = [];

  constructor(private inventoryService: InventoryService, private holderService:HolderService, private snackBar:MatSnackBar) {
  }

  getAllHolderIds() {
    this.holderService.getAllMedicalHolderInventoryIds().subscribe(
      (response) => {
        this.holders = response.data;
        console.log(this.holders);
      },
      (error) => {
        console.error('Error fetching medicine IDs:', error);
      }
    );
  }

  inventoryForm = new FormGroup({
    holderId: new FormControl("", Validators.required),
    name: new FormControl("", Validators.required),
    qty: new FormControl("", Validators.required),
    unitPrice: new FormControl("", Validators.required),
    manufactureDate: new FormControl("", Validators.required),
    expiredDate: new FormControl("", Validators.required),
  });


  createInventory() {
    this.inventoryService
      .createMedicalInventory(
        this.inventoryForm.get('holderId')?.value,
        this.inventoryForm.get('name')?.value,
        this.inventoryForm.get('qty')?.value,
        this.inventoryForm.get('unitPrice')?.value,
        this.inventoryForm.get('manufactureDate')?.value,
        this.inventoryForm.get('expiredDate')?.value,
      )
      .subscribe(
        (response) => {
          this.snackBar.open("Medicine Inventory Data Created SuccessFully",'close')
          console.log('Inventory created successfully:', response);
        },
        (error) => {
          console.error('Error creating inventory:', error);
        }
      );
  }

  getAllInventories() {
    this.inventoryService.getAllInventories().subscribe(
      (response) => {
        console.log(response)
        this.inventories = response.data;
      },
      (error) => {
        console.error('Error getting all Inventories', error);
      }
    );
  }

  deleteInventory(id: string): void {
    this.inventoryService.deleteInventory(id).subscribe(
      (response) => {
        console.log('Doctor deleted successfully:', response);
        this.snackBar.open("Medicine Inventory Deleted SuccessFully",'close')

      },
      (error) => {
        console.error('Error deleting doctor:', error);
      }
    );
  }

}
