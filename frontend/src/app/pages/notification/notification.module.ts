import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NotificationRoutingModule } from './notification-routing.module';
import {JoinGroupService} from "../../share/service/join-group.service";
import { PublishCenterGroupDetailComponent } from './publish-center/publish-center-group-detail/publish-center-group-detail.component';

@NgModule({
  declarations: [PublishCenterGroupDetailComponent],
  imports: [
    CommonModule,
    NotificationRoutingModule
  ],
  providers: [
    JoinGroupService
  ]
})
export class NotificationModule { }
