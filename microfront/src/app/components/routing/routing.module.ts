import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {HomeComponent} from "../home/home.component";
import {LoginComponent} from "../login/login.component";
import {AccountComponent} from "../account/account.component";
import {ProfileComponent} from "../profile/profile.component";
import {TransfersComponent} from "../transfers/transfers.component";

const routes: Routes = [
  {path: '', component: HomeComponent},
  {path: 'login', component: LoginComponent},
  {path: 'profile', component: ProfileComponent},
  {path: 'transfers', component: TransfersComponent},
  {path: 'account', component: AccountComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class RoutingModule { }
