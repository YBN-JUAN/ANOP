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
import {GroupMessageComponent} from './pages/notification/subscription-center/group-message/group-message.component';
import {CookieService} from 'ngx-cookie-service';
import {GlobalInterceptor} from './interceptor/global-interceptor.interceptor';
import {LoginComponent} from './pages/welcome/login/login.component';
import {RegisterComponent} from './pages/welcome/register/register.component';
import {HeadButtonComponent} from './share/head-button/head-button.component';
import {PublishCenterGroupListComponent} from './pages/notification/publish-center/publish-center-group-list/publish-center-group-list.component';
import {AllTodoComponent} from './pages/memorandum/all-todo/all-todo.component';
import {TodoItemComponent} from './pages/memorandum/todo-item/todo-item.component';
import {RequestListComponent} from './pages/notification/examine-center/request-list/request-list.component';
import {ImpTodoComponent} from './pages/memorandum/imp-todo/imp-todo.component';
import {FavorTodoComponent} from './pages/memorandum/favor-todo/favor-todo.component';
import {HistTodoComponent} from './pages/memorandum/hist-todo/hist-todo.component';
import {AddTodoComponent} from './pages/memorandum/add-todo/add-todo.component';
import {EditTodoComponent} from './pages/memorandum/edit-todo/edit-todo.component';
import {AllCateComponent} from './pages/memorandum/all-cate/all-cate.component';
import {CateUnitComponent} from './pages/memorandum/cate-unit/cate-unit.component';
import {CateDetailComponent} from './pages/memorandum/cate-detail/cate-detail.component';
import {NewNotificationComponent} from './pages/notification/publish-center/new-notification/new-notification.component';
import {CreateGroupComponent} from './pages/notification/publish-center/create-group/create-group.component';
import {ManageGroupComponent} from './pages/notification/publish-center/manage-group/manage-group.component';
import {UpdateNotificationComponent} from './pages/notification/publish-center/update-notification/update-notification.component';
import {ReaderListComponent} from './pages/notification/publish-center/reader-list/reader-list.component';

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
    GroupMessageComponent,
    HeadButtonComponent,
    PublishCenterGroupListComponent,
    AllTodoComponent,
    TodoItemComponent,
    RequestListComponent,
    ImpTodoComponent,
    FavorTodoComponent,
    HistTodoComponent,
    AddTodoComponent,
    EditTodoComponent,
    AllCateComponent,
    CateUnitComponent,
    CateDetailComponent,
    NewNotificationComponent,
    CreateGroupComponent,
    ManageGroupComponent,
    UpdateNotificationComponent,
    ReaderListComponent
  ],
  imports: [
    ReactiveFormsModule,
    BrowserModule,
    AppRoutingModule,
    IconsProviderModule,
    NgZorroAntdModule,
    FormsModule,
    HttpClientModule,
    BrowserAnimationsModule,
  ],
  providers: [{provide: NZ_I18N, useValue: zh_CN},
    {provide: HTTP_INTERCEPTORS, useClass: GlobalInterceptor, multi: true},
    CookieService],
  bootstrap: [AppComponent]
})
export class AppModule {
}
