import {Component, Input, OnInit} from '@angular/core';
import {NotificationModel} from '../../../../share/model/notification.model';
import {HttpErrorResponse} from '@angular/common/http';
import {PublishCenterService} from '../../../../share/service/publish-center.service';

@Component({
  selector: 'app-update-notification',
  templateUrl: './update-notification.component.html',
  styleUrls: ['./update-notification.component.css']
})
export class UpdateNotificationComponent implements OnInit {

  @Input() service: PublishCenterService;
  @Input() title: string;
  @Input() content: string;
  @Input() nid: number;
  @Input() gid: number;
  isVisible = false;
  isOkLoading = false;

  constructor() {
  }

  ngOnInit(): void {
  }

  handleOk(): void {
    this.submitFrom();
    console.log('调用了handleOk')
  }

  handleCancel(): void {
    this.isVisible = false;
  }

  submitFrom() {
    this.isOkLoading = true;
    const model = new NotificationModel(this.title, this.content, this.gid);
    this.service.updateNotification(this.nid, model).subscribe(
      () => {
        this.service.msg.success('编辑成功。');
        this.isVisible = false;
        this.isOkLoading = false;
      }, (error: HttpErrorResponse) => {
        this.service.msg.error(error.message);
        console.log(error);
      }
    );
  }
}
