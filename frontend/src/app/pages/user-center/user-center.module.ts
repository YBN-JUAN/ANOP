import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { UserCenterRoutingModule } from './user-center-routing.module';
import { EditProfileComponent } from './edit-profile/edit-profile.component';
import { EditPasswordComponent } from './edit-password/edit-password.component';
import { NgZorroAntdModule, NZ_I18N, zh_CN } from 'ng-zorro-antd';
import {FormsModule} from '@angular/forms';

@NgModule({
  declarations: [EditProfileComponent, EditPasswordComponent],
  imports: [
    CommonModule,
    UserCenterRoutingModule,
    NgZorroAntdModule,
    FormsModule
  ]
})
export class UserCenterModule { }
