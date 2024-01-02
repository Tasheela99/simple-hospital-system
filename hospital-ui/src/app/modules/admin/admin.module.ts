import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AdminRoutingModule } from './admin-routing.module';
import { AdminComponent } from './admin.component';
import { AdminVerificationComponent } from './admin-verification/admin-verification.component';
import { AllAdminsComponent } from './all-admins/all-admins.component';
import { NewDoctorComponent } from './new-doctor/new-doctor.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import {MatToolbarModule} from "@angular/material/toolbar";
import {MatButtonModule} from "@angular/material/button";
import {MatIconModule} from "@angular/material/icon";
import {MatSidenavModule} from "@angular/material/sidenav";
import {MatCardModule} from "@angular/material/card";
import {CarouselModule} from "ngx-owl-carousel-o";
import { DashboardCarouselComponent } from './dashboard-carousel/dashboard-carousel.component';
import {MatDividerModule} from "@angular/material/divider";
import {MatFormFieldModule} from "@angular/material/form-field";
import {MatInputModule} from "@angular/material/input";
import {MatTableModule} from "@angular/material/table";
import { ChartComponent } from './chart/chart.component';
import {NgApexchartsModule} from "ng-apexcharts";


@NgModule({
  declarations: [
    AdminComponent,
    AdminVerificationComponent,
    AllAdminsComponent,
    NewDoctorComponent,
    DashboardComponent,
    DashboardCarouselComponent,
    ChartComponent,
  ],
    imports: [
        CommonModule,
        AdminRoutingModule,
        MatToolbarModule,
        MatButtonModule,
        MatIconModule,
        MatSidenavModule,
        MatCardModule,
        CarouselModule,
        MatDividerModule,
        MatFormFieldModule,
        MatInputModule,
        MatTableModule,
        NgApexchartsModule,

    ]
})
export class AdminModule { }
