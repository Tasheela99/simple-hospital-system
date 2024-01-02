import {Component, ViewChild} from '@angular/core';

import {
  ApexAxisChartSeries,
  ApexChart,
  ApexXAxis,
  ApexTitleSubtitle
} from "ng-apexcharts";
export type ChartOptions = {
  series: ApexAxisChartSeries | any;
  chart: ApexChart | any;
  xaxis: ApexXAxis | any;
  title: ApexTitleSubtitle | any;
};

@Component({
  selector: 'app-chart',
  templateUrl: './chart.component.html',
  styleUrl: './chart.component.scss'
})
export class ChartComponent {

  @ViewChild("chart") chart: ChartComponent | any;
  public chartOptions: Partial<ChartOptions>;

  constructor() {
    this.chartOptions = {
      series: [
        {
          name: "My-series",
          data: []
        }
      ],
      chart: {
        height: 350,
        type: "bar"
      },
      title: {
        text: "User Reports"
      },
      xaxis: {
        categories: ["Patients", "Doctors",  "Inventory Holders",  "Medicine"]
      }
    };
  }

}
