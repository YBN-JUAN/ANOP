import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {UserCenterRoutingModule} from './user-center-routing.module';
import {EditPasswordComponent} from './edit-password/edit-password.component';
import {NgZorroAntdModule} from 'ng-zorro-antd';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';

@NgModule({
  declarations: [EditPasswordComponent],
  imports: [
    CommonModule,
    UserCenterRoutingModule,
    NgZorroAntdModule,
    FormsModule,
    ReactiveFormsModule
  ]
})
export class UserCenterModule {
}
