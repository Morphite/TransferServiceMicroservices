import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {HomeComponent} from "../home/home.component";
import {LoginComponent} from "../login/login.component";
import {AccountComponent} from "../account/account.component";

const routes: Routes = [
  {path: '', component: HomeComponent},
  {path: 'login', component: LoginComponent},
  {path: 'profile', component: HomeComponent},
  {path: 'transfer', component: HomeComponent},
  {path: 'account', component: AccountComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class RoutingModule { }
