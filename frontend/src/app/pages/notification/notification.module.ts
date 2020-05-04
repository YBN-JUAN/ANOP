import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NotificationRoutingModule } from './notification-routing.module';
import {JoinGroupService} from "./subscription-center/join-group/join-group.service";
import { PublishCenterGroupDetailComponent } from './publish-center/publish-center-group-detail/publish-center-group-detail.component';
import {GroupMessageService} from "./subscription-center/group-message/group-message.service";

@NgModule({
  declarations: [PublishCenterGroupDetailComponent],
  imports: [
    CommonModule,
    NotificationRoutingModule
  ],
  providers: [
    JoinGroupService,
    GroupMessageService
  ]
})
export class NotificationModule { }
