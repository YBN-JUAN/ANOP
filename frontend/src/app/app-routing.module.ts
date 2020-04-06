import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {WelcomeComponent} from './pages/welcome/welcome.component';
import {NotificationComponent} from './pages/notification/notification.component';
import {MemorandumComponent} from './pages/memorandum/memorandum.component';
import {LoginComponent} from './pages/welcome/login/login.component';
import {RegisterComponent} from './pages/welcome/register/register.component';

const routes: Routes = [
  {
    path: 'welcome', component: WelcomeComponent,
    children:[
      { path: 'login', component: LoginComponent },
      { path: 'register', component: RegisterComponent },
      { path: '**', component: LoginComponent },
      { path: '', component:LoginComponent }
    ]
  },
  { path: 'notification', component: NotificationComponent },
  { path: 'memorandum', component: MemorandumComponent},
  { path: '', redirectTo: '/welcome/login', pathMatch: 'full'},
  { path: '**', redirectTo: '/welcome/login', pathMatch: 'full'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
