import {Component, OnInit} from '@angular/core';
import {OwlOptions} from "ngx-owl-carousel-o";

@Component({
  selector: 'app-dashboard-carousel',
  templateUrl: './dashboard-carousel.component.html',
  styleUrl: './dashboard-carousel.component.scss'
})
export class DashboardCarouselComponent implements OnInit{

  ngOnInit() {
  }

  services: any[] = [
    {
      serviceImage: 'https://img.freepik.com/free-vector/doctors-concept-illustration_114360-1515.jpg?w=1380&t=st=1703426232~exp=1703426832~hmac=0e7d876a516bb2a15579acd33ba361610212dcdc47902688ce56cc553be188ea',
      titleText: 'Doctors',
      subTitleText: 'Manage Doctors',
      mainImage: 'https://img.freepik.com/free-vector/doctors-concept-illustration_114360-1515.jpg?w=1380&t=st=1703426232~exp=1703426832~hmac=0e7d876a516bb2a15579acd33ba361610212dcdc47902688ce56cc553be188ea',
      description: ''
    },
    {
      serviceImage: 'https://img.freepik.com/free-vector/doctor-taking-blood-sample-old-patient-hospital-man-doing-checkup-examination-clinic-senior-sitting-chair_575670-1318.jpg?w=996&t=st=1703425975~exp=1703426575~hmac=5a6085f1e94d46a4190bed0380a63841b2c9d8f121cf5e0aed96c6176ef1e5b4',
      titleText: 'Patients',
      subTitleText: 'Manage Patients',
      mainImage: 'https://img.freepik.com/free-vector/doctor-taking-blood-sample-old-patient-hospital-man-doing-checkup-examination-clinic-senior-sitting-chair_575670-1318.jpg?w=996&t=st=1703425975~exp=1703426575~hmac=5a6085f1e94d46a4190bed0380a63841b2c9d8f121cf5e0aed96c6176ef1e5b4',
      description: ''
    },
    {
      serviceImage: 'https://img.freepik.com/free-vector/medicine-concept-illustration_114360-2802.jpg?w=826&t=st=1703426297~exp=1703426897~hmac=f79a6f97823d589f80a3f678bf9e3e23fab218aa5a2dbb95a864fb0686aa6bfb',
      titleText: 'Medicine',
      subTitleText: 'Manage Medicine',
      mainImage: 'https://img.freepik.com/free-vector/medicine-concept-illustration_114360-2802.jpg?w=826&t=st=1703426297~exp=1703426897~hmac=f79a6f97823d589f80a3f678bf9e3e23fab218aa5a2dbb95a864fb0686aa6bfb',
      description: ''
    },
    {
      serviceImage: 'https://img.freepik.com/free-vector/checking-boxes-concept-illustration_114360-2465.jpg?w=826&t=st=1703426107~exp=1703426707~hmac=8fa65fdaf9d8a533cf7dacc71d5e25096fb0c7c04cee709a320d92e5780f1b3a',
      titleText: 'Inventory Holders',
      subTitleText: 'Manage Inventory Holders',
      mainImage: 'https://img.freepik.com/free-vector/checking-boxes-concept-illustration_114360-2465.jpg?w=826&t=st=1703426107~exp=1703426707~hmac=8fa65fdaf9d8a533cf7dacc71d5e25096fb0c7c04cee709a320d92e5780f1b3a',
      description: ''
    },
    {
      serviceImage: 'https://img.freepik.com/free-vector/pharmacist_23-2148185760.jpg?w=826&t=st=1703426069~exp=1703426669~hmac=6a40e04992854bdf650b1342f3509145a908c95f6d116241c0c68920e4557059',
      titleText: 'Medicine Inventory',
      subTitleText: 'Manage Medicine Inventory',
      mainImage: 'https://img.freepik.com/free-vector/pharmacist_23-2148185760.jpg?w=826&t=st=1703426069~exp=1703426669~hmac=6a40e04992854bdf650b1342f3509145a908c95f6d116241c0c68920e4557059',
      description: ''
    }
  ];




  customOptions: OwlOptions = {
    loop: true,
    mouseDrag: true,
    touchDrag: true,
    pullDrag: true,
    navSpeed: 700,
    margin:5,
    autoplay:true,
    responsive: {
      0: {
        items: 1
      },
      400: {
        items: 2
      },
      740: {
        items: 3
      },
      940: {
        items: 5
      }
    },
  }


}
