import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {NotFoundComponent} from "./modules/components/not-found/not-found.component";

const routes: Routes = [
  {path: '', redirectTo: 'security', pathMatch: 'full'},
  {path: 'security', loadChildren: () => import('./modules/security/security.module').then(m => m.SecurityModule)},
  {path: 'admin', loadChildren: () => import('./modules/admin/admin.module').then(m => m.AdminModule)},
  {path: 'shared', loadChildren: () => import('./modules/shared/shared.module').then(m => m.SharedModule)},
  {path: 'hospital', loadChildren: () => import('./modules/hospital/hospital.module').then(m => m.HospitalModule)},
  {path: '**', component: NotFoundComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
