import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {LoginComponent} from "./login/login.component";
import {HomeComponent} from "./home/home.component";
import {ForgotPasswordComponent} from "./forgot-password/forgot-password.component";
import {VerifyResetCodeComponent} from "./verify-reset-code/verify-reset-code.component";
import {ResetPasswordComponent} from "./reset-password/reset-password.component";


const routes: Routes = [
  { path: '', component: LoginComponent },
  { path: 'home', component: HomeComponent },
  { path: 'forgot-password', component: ForgotPasswordComponent },
  { path: 'verify-reset-code', component: VerifyResetCodeComponent },
  { path: 'reset-password', component: ResetPasswordComponent },
  { path: 'login', component: LoginComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
