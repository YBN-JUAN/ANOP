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
import {GroupMessageComponent} from './pages/notification/subscription-center/group-message/group-message.component';
import {GroupDetailComponent} from './pages/notification/subscription-center/group-detail/group-detail.component';
import {GroupListComponent} from './pages/notification/subscription-center/group-list/group-list.component';
import {JoinGroupComponent} from './pages/notification/subscription-center/join-group/join-group.component';
import {AllTodoComponent} from './pages/memorandum/all-todo/all-todo.component';
import {PublishCenterGroupListComponent} from './pages/notification/publish-center/publish-center-group-list/publish-center-group-list.component';
import {PublishCenterGroupDetailComponent} from './pages/notification/publish-center/publish-center-group-detail/publish-center-group-detail.component';

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
      { path: 'publish', component: PublishCenterComponent,
        children:[
          { path: 'group-list', component: PublishCenterGroupListComponent},
          { path: 'group-detail/:id', component: PublishCenterGroupDetailComponent},
          { path: '**', component: PublishCenterGroupListComponent },
          { path: '', component: PublishCenterGroupListComponent}
        ]
      },
      { path: 'subscription', component: SubscriptionCenterComponent,
        children:[
          { path: 'group-message', component: GroupMessageComponent },
          { path: 'group-detail/:id', component: GroupDetailComponent },
          { path: 'group-list', component: GroupListComponent },
          { path: 'join-group', component: JoinGroupComponent },
          { path: '**', component: GroupListComponent },
          { path: '', component: GroupListComponent }
        ]
      },
      { path: 'examine', component: ExamineCenterComponent },
      { path: '**', component: PublishCenterComponent },
      { path: '', component: PublishCenterComponent }
    ]
  },
  { path: 'memorandum', component: MemorandumComponent,
    children:[
      { path: 'all', component: AllTodoComponent },
      { path: 'subscription', component: SubscriptionCenterComponent,
        children:[
          { path: 'group', component: GroupMessageComponent },
          { path: 'group-detail', component: GroupDetailComponent },
          { path: 'group-list', component: GroupListComponent },
          { path: 'join-group', component: JoinGroupComponent },
          { path: '**', component: GroupListComponent },
          { path: '', component: GroupListComponent }
        ]
      },
      { path: 'examine', component: ExamineCenterComponent },
      { path: '**', component: AllTodoComponent },
      { path: '', component: AllTodoComponent }
    ]
  },
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
