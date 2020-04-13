import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { NotificationRoutingModule } from './notification-routing.module';
import { DropMenuComponent } from './drop-menu/drop-menu.component';
import { ExamineCenterComponent } from './examine-center/examine-center.component';
import { PublishCenterComponent } from './publish-center/publish-center.component';
import { SubscriptionCenterComponent } from './subscription-center/subscription-center.component';


@NgModule({
  declarations: [DropMenuComponent, ExamineCenterComponent, PublishCenterComponent, SubscriptionCenterComponent],
  imports: [
    CommonModule,
    NotificationRoutingModule
  ]
})
export class NotificationModule { }
