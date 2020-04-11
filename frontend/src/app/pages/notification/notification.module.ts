import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { NotificationRoutingModule } from './notification-routing.module';
import { DropMenuComponent } from './drop-menu/drop-menu.component';


@NgModule({
  declarations: [DropMenuComponent],
  imports: [
    CommonModule,
    NotificationRoutingModule
  ]
})
export class NotificationModule { }
