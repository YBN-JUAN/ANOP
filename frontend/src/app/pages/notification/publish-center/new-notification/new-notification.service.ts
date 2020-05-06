import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {NotificationModel} from '../../../../share/model/notification.model';

@Injectable({
  providedIn: 'root'
})
export class NewNotificationService {
  private url = 'http://localhost:8080/v1/pub/groups';
  private suffix = 'notifications'

  constructor(private http: HttpClient) {
  }

  sendNotification(notification: NotificationModel) {
    return this.http.post(`${this.url}/${notification.gid}/${this.suffix}`, {
      title: notification.title,
      content: notification.content
    });
  }
}
