import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NotificationRoutingModule } from './notification-routing.module';
import {JoinGroupService} from "../../share/service/join-group.service";

@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    NotificationRoutingModule
  ],
  providers: [
    JoinGroupService
  ]
})
export class NotificationModule { }
