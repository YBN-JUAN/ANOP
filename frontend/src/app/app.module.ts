import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { IconsProviderModule } from './icons-provider.module';
import { NgZorroAntdModule, NZ_I18N, zh_CN } from 'ng-zorro-antd';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { registerLocaleData } from '@angular/common';
import zh from '@angular/common/locales/zh';
import { WelcomeComponent } from './pages/welcome/welcome.component';
import { NotificationComponent } from './pages/notification/notification.component';
import { MemorandumComponent } from './pages/memorandum/memorandum.component';
import {UserCenterComponent} from './pages/user-center/user-center.component';
import {DropMenuComponent} from './share/drop-menu/drop-menu.component';
import { HeadBarComponent } from './share/head-bar/head-bar.component';
import { EditProfileComponent } from './pages/user-center/edit-profile/edit-profile.component';
import { EditPasswordComponent } from './pages/user-center/edit-password/edit-password.component';
import {ExamineCenterComponent} from './pages/notification/examine-center/examine-center.component';
import {PublishCenterComponent} from './pages/notification/publish-center/publish-center.component';
import {SubscriptionCenterComponent} from './pages/notification/subscription-center/subscription-center.component';

registerLocaleData(zh);

@NgModule({
  declarations: [
    AppComponent,
    WelcomeComponent,
    NotificationComponent,
    MemorandumComponent,
    UserCenterComponent,
    DropMenuComponent,
    HeadBarComponent,
    EditProfileComponent,
    EditPasswordComponent,
    ExamineCenterComponent,
    PublishCenterComponent,
    SubscriptionCenterComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    IconsProviderModule,
    NgZorroAntdModule,
    FormsModule,
    HttpClientModule,
    BrowserAnimationsModule
  ],
  providers: [{ provide: NZ_I18N, useValue: zh_CN }],
  bootstrap: [AppComponent]
})
export class AppModule { }
