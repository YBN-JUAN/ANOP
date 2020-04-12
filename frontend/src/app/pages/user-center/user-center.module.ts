import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { UserCenterRoutingModule } from './user-center-routing.module';
import { EditProfileComponent } from './edit-profile/edit-profile.component';
import { EditPasswordComponent } from './edit-password/edit-password.component';


@NgModule({
  declarations: [EditProfileComponent, EditPasswordComponent],
  imports: [
    CommonModule,
    UserCenterRoutingModule
  ]
})
export class UserCenterModule { }
