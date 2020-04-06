import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {WelcomeComponent} from './pages/welcome/welcome.component';
import {NotificationComponent} from './pages/notification/notification.component';
import {MemorandumComponent} from './pages/memorandum/memorandum.component';

const routes: Routes = [
  { path: 'welcome/login', component: WelcomeComponent },
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
