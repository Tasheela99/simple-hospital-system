import {Inject, NgModule, OnInit, PLATFORM_ID} from '@angular/core';
import {BrowserModule, provideClientHydration} from '@angular/platform-browser';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {MatFormFieldModule} from "@angular/material/form-field";
import {MatInputModule} from "@angular/material/input";
import {MatIconModule} from "@angular/material/icon";
import {MatSelectModule} from "@angular/material/select";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {HTTP_INTERCEPTORS, HttpClientModule, provideHttpClient, withFetch} from "@angular/common/http";
import {MatNativeDateModule} from "@angular/material/core";
import {MatDatepickerModule} from "@angular/material/datepicker";
import {MatCardModule} from "@angular/material/card";
import {CarouselModule} from "ngx-owl-carousel-o";
import {NgApexchartsModule} from "ng-apexcharts";
import {DatePipe, isPlatformBrowser} from "@angular/common";
import { NotFoundComponent } from './modules/components/not-found/not-found.component';
import {MatButtonModule} from "@angular/material/button";
import {LoadingComponent} from "./modules/hospital/components/loading/loading.component";
import {MatProgressSpinner, MatProgressSpinnerModule} from "@angular/material/progress-spinner";
import {HttpManagerInterceptor} from "./modules/shared/interceptors/http-manager.interceptor";
import {AuthGuard} from "./modules/shared/guards/auth.guard";



@NgModule({
  declarations: [
    AppComponent,
    NotFoundComponent,
    LoadingComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatFormFieldModule,
    MatInputModule,
    MatIconModule,
    MatSelectModule,
    FormsModule,
    HttpClientModule,
    MatDatepickerModule,
    MatNativeDateModule,
    ReactiveFormsModule,
    MatCardModule,
    CarouselModule,
    NgApexchartsModule,
    MatButtonModule,
    MatProgressSpinnerModule
  ],
  providers: [
    provideHttpClient(withFetch()),
    DatePipe,
    {provide:HTTP_INTERCEPTORS,useClass:HttpManagerInterceptor,multi:true},
    AuthGuard
  ],
  exports: [
    LoadingComponent
  ],
  bootstrap: [AppComponent]
})
export class AppModule implements OnInit{
  constructor(@Inject(PLATFORM_ID) private platformId: Object) {}

  ngOnInit(): void {
    if (isPlatformBrowser(this.platformId)) {
      window.alert('This code is running on the client side.');
    } else {
      console.log('This code is running on the server side.');
    }
  }
}
