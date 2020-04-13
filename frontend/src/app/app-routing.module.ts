import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {WelcomeComponent} from './pages/welcome/welcome.component';
import {NotificationComponent} from './pages/notification/notification.component';
import {MemorandumComponent} from './pages/memorandum/memorandum.component';
import {LoginComponent} from './pages/welcome/login/login.component';
import {RegisterComponent} from './pages/welcome/register/register.component';
import { UserCenterComponent } from './pages/user-center/user-center.component';
import { EditPasswordComponent } from './pages/user-center/edit-password/edit-password.component';
import { EditProfileComponent } from './pages/user-center/edit-profile/edit-profile.component';
import {PublishCenterComponent} from './pages/notification/publish-center/publish-center.component';
import {SubscriptionCenterComponent} from './pages/notification/subscription-center/subscription-center.component';
import {ExamineCenterComponent} from './pages/notification/examine-center/examine-center.component';

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
  { path: 'notification', component: NotificationComponent,
    children:[
      { path: 'publish', component: PublishCenterComponent },
      { path: 'subscription', component: SubscriptionCenterComponent },
      { path: 'examine', component: ExamineCenterComponent },
      { path: '**', component: PublishCenterComponent },
      { path: '', component: PublishCenterComponent }
    ]
  },
  { path: 'memorandum', component: MemorandumComponent},
  { path: 'user', component: UserCenterComponent,
    children:[
      { path: 'profile', component: EditProfileComponent },
      { path: 'password', component: EditPasswordComponent },
      { path: '**', component: EditProfileComponent },
      { path: '', component: EditProfileComponent }
    ]
  },
  { path: '', redirectTo: '/welcome/login', pathMatch: 'full'},
  { path: '**', redirectTo: '/welcome/login', pathMatch: 'full'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
