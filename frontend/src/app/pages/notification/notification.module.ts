import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {NotificationRoutingModule} from './notification-routing.module';
import {JoinGroupService} from './subscription-center/join-group/join-group.service';
import {GroupMessageService} from './subscription-center/group-message/group-message.service';
import {NzButtonModule, NzFormModule, NzInputModule, NzModalModule} from 'ng-zorro-antd';
import {ReactiveFormsModule} from '@angular/forms';

@NgModule({
  imports: [
    CommonModule,
    NotificationRoutingModule,
    NzModalModule,
    NzFormModule,
    ReactiveFormsModule,
    NzInputModule,
    NzButtonModule
  ],
  providers: [
    JoinGroupService,
    GroupMessageService
  ],
  declarations: []
})
export class NotificationModule { }
