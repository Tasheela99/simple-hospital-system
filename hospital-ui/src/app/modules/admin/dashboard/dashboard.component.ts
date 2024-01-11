import {ChangeDetectorRef, Component, Inject, OnInit, PLATFORM_ID, ViewChild} from '@angular/core';
import {MatSidenav} from "@angular/material/sidenav";
import {DoctorService} from "../../shared/services/doctor.service";
import {PatientService} from "../../shared/services/patient.service";
import {MedicineService} from "../../shared/services/medicine.service";
import {HolderService} from "../../shared/services/holder.service";
import {isPlatformBrowser} from "@angular/common";
import {OwlOptions} from "ngx-owl-carousel-o";
import {UserService} from "../../shared/services/user.service";
import {
  ApexAxisChartSeries,
  ApexChart,
  ApexXAxis,
  ApexTitleSubtitle,
} from "ng-apexcharts";
export type ChartOptions = {
  series: ApexAxisChartSeries | any;
  chart: ApexChart | any;
  xaxis: ApexXAxis | any;
  title: ApexTitleSubtitle | any;
  colors: string[];
};

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.scss'
})
export class DashboardComponent implements OnInit {
  displayedColumns: string[] = ['index', 'userid', 'email', 'firstname', 'lastname'];

  @ViewChild('sidenav') sidenav: MatSidenav | undefined;
  showFiller = false;


  @ViewChild("chart") chart: DashboardComponent | any;
  public chartOptions: Partial<ChartOptions> | any;


  patientCount!: number;
  doctorCount!: number;
  inventoryHolderCount!: number;
  medicineCount!: number;
  isBrowser: boolean;

  cards: any[] = [];
  users: object[] = [];
  countResult: any;

  constructor(
    private doctorService: DoctorService,
    private patientService: PatientService,
    private medicineService: MedicineService,
    private holderService: HolderService,
    private userService: UserService,
    private cdr: ChangeDetectorRef,
    @Inject(PLATFORM_ID) private platformId: Object
  ) {
    this.isBrowser = isPlatformBrowser(this.platformId);
  }

  getPatientCount(): Promise<number> {
    return new Promise<number>((resolve, reject) => {
      this.patientService.count().subscribe(
        (result) => {
          this.patientCount = result.data;
          console.log('Patient count:', this.patientCount);
          resolve(this.patientCount);
        },
        (error) => {
          console.error('Error fetching patient count', error);
          reject(error);
        }
      );
    });
  }

  getDocCount(): Promise<number> {
    return new Promise<number>((resolve, reject) => {
      this.doctorService.count().subscribe(
        (result) => {
          this.doctorCount = result.data;
          console.log('Patient count:', this.doctorCount);
          resolve(this.doctorCount);
        },
        (error) => {
          console.error('Error fetching patient count', error);
          reject(error);
        }
      );
    });
  }
  getInventoryCount(): Promise<number> {
    return new Promise<number>((resolve, reject) => {
      this.holderService.count().subscribe(
        (result) => {
          this.inventoryHolderCount = result.data;
          console.log('Patient count:', this.inventoryHolderCount);
          resolve(this.inventoryHolderCount);
        },
        (error) => {
          console.error('Error fetching patient count', error);
          reject(error);
        }
      );
    });
  }

  getMedicineCount(): Promise<number> {
    return new Promise<number>((resolve, reject) => {
      this.medicineService.count().subscribe(
        (result) => {
          this.medicineCount = result.data;
          console.log('Patient count:', this.medicineCount);
          resolve(this.medicineCount);
        },
        (error) => {
          console.error('Error fetching patient count', error);
          reject(error);
        }
      );
    });
  }

  getCounts(): Promise<number[]> {
    const patientCountPromise = this.getPatientCount();
    const doctorCountPromise = this.getDocCount();
    const inventoryHolderCountPromise = this.getInventoryCount();
    const medicineCountPromise = this.getMedicineCount();

    return Promise.all([patientCountPromise, doctorCountPromise, inventoryHolderCountPromise, medicineCountPromise]);
  }

  ngOnInit(): void {
    this.getCounts().then(([patientCount, doctorCount, inventoryHolderCount, medicineCount]) => {
      this.updateCards(patientCount, doctorCount, inventoryHolderCount, medicineCount);
      this.updateChart(patientCount, doctorCount, inventoryHolderCount, medicineCount);
    }).catch((error) => {
      console.error('Error in ngOnInit:', error);
    });

    this.getALlUsers();
  }

  getALlUsers() {
    this.userService.getAllUsers().subscribe(
      (response) => {
        console.log(response)
        this.users = response.data;

      },
      (error) => {
        console.error('Error getting all doctors', error);
      }
    );
  }

  updateChart(patientCount: number, doctorCount: number, inventoryHolderCount: number, medicineCount: number): void {
    this.chartOptions = {
      series: [
        {
          name: "Hospital Data",
          data: [patientCount, doctorCount, inventoryHolderCount, medicineCount]
        }
      ],
      chart: {
        width:'100%',
        height: 350,
        type: "bar",
        animations: {
          enabled: true,
          easing: 'easeinout',
          speed: 800,
          animateGradually: {
            enabled: true,
            delay: 150
          },
          dynamicAnimation: {
            enabled: true,
            speed: 350
          }
        }
      },
      colors: [
        "#008FFB",
        "#00E396",
        "#FEB019",
        "#FF4560"
      ],
      title: {
        text: "Storage Details"
      },
      xaxis: {
        categories: ["Patients", "Doctors",  "Inventory Holders",  "Medicine"]
      },
    };
  }
  updateCards(patientCount: number, doctorCount: number, inventoryHolderCount: number, medicineCount: number): void {
    this.cards = [
      {
        name: 'Patients',
        countIs: patientCount,
        iconName:'accessible'
      },
      {
        name: 'Doctors',
        countIs: doctorCount,
        iconName:'local_pharmacy'
      },
      {
        name: 'Inventory Holders',
        countIs: inventoryHolderCount,
        iconName:'store'
      },
      {
        name: 'Medicines',
        countIs: medicineCount,
        iconName:'queue'
      },
    ];
  }


}
