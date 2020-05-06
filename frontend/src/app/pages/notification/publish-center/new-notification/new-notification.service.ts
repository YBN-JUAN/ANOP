import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {NotificationModel} from '../../../../share/model/notification.model';
import {ApiUrlResource} from '../../../../share/resource/api-url.resource';

@Injectable({
  providedIn: 'root'
})
export class NewNotificationService {
  private suffix = 'notifications'

  constructor(private http: HttpClient) {
  }

  sendNotification(notification: NotificationModel) {
    return this.http.post(`${ApiUrlResource.publishGroupUrl}/${notification.gid}/${this.suffix}`, {
      title: notification.title,
      content: notification.content
    });
  }
}
