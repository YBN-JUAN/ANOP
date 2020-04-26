import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {IconsProviderModule} from './icons-provider.module';
import {NgZorroAntdModule, NZ_I18N, zh_CN} from 'ng-zorro-antd';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {registerLocaleData} from '@angular/common';
import zh from '@angular/common/locales/zh';
import {WelcomeComponent} from './pages/welcome/welcome.component';
import {NotificationComponent} from './pages/notification/notification.component';
import {MemorandumComponent} from './pages/memorandum/memorandum.component';
import {UserCenterComponent} from './pages/user-center/user-center.component';
import {DropMenuComponent} from './share/drop-menu/drop-menu.component';
import {EditProfileComponent} from './pages/user-center/edit-profile/edit-profile.component';
import {EditPasswordComponent} from './pages/user-center/edit-password/edit-password.component';
import {ExamineCenterComponent} from './pages/notification/examine-center/examine-center.component';
import {PublishCenterComponent} from './pages/notification/publish-center/publish-center.component';
import {SubscriptionCenterComponent} from './pages/notification/subscription-center/subscription-center.component';
import {GroupListComponent} from './pages/notification/subscription-center/group-list/group-list.component';
import {GroupDetailComponent} from './pages/notification/subscription-center/group-detail/group-detail.component';
import {JoinGroupComponent} from './pages/notification/subscription-center/join-group/join-group.component';
import {GroupComponent} from './pages/notification/subscription-center/group/group.component';
import {CookieService} from 'ngx-cookie-service';
import {GlobalInterceptor} from './interceptor/global-interceptor.interceptor';
import {LoginComponent} from './pages/welcome/login/login.component';
import {RegisterComponent} from './pages/welcome/register/register.component';
import {HeadButtonComponent} from './share/head-button/head-button.component';
import {PublishCenterGroupListComponent} from './pages/notification/publish-center/publish-center-group-list/publish-center-group-list.component';
import {JoinGroupService} from './share/service/join-group.service';
import {AllTodoComponent} from './pages/memorandum/all-todo/all-todo.component';
import { TodoItemComponent } from './pages/memorandum/todo-item/todo-item.component';
import { RequestListComponent } from './pages/notification/examine-center/request-list/request-list.component';

registerLocaleData(zh);

@NgModule({
  declarations: [
    AppComponent,
    WelcomeComponent,
    LoginComponent,
    RegisterComponent,
    NotificationComponent,
    MemorandumComponent,
    UserCenterComponent,
    DropMenuComponent,
    EditProfileComponent,
    EditPasswordComponent,
    ExamineCenterComponent,
    PublishCenterComponent,
    SubscriptionCenterComponent,
    GroupListComponent,
    GroupDetailComponent,
    JoinGroupComponent,
    GroupComponent,
    HeadButtonComponent,
    PublishCenterGroupListComponent,
    AllTodoComponent,
    TodoItemComponent,
    RequestListComponent
  ],
  imports: [
    ReactiveFormsModule,
    BrowserModule,
    AppRoutingModule,
    IconsProviderModule,
    NgZorroAntdModule,
    FormsModule,
    HttpClientModule,
    BrowserAnimationsModule
  ],
  providers: [{provide: NZ_I18N, useValue: zh_CN},
    {provide: HTTP_INTERCEPTORS, useClass: GlobalInterceptor, multi: true},
    CookieService,
    JoinGroupService],
  bootstrap: [AppComponent]
})
export class AppModule {
}
