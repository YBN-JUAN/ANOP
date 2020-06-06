import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Params} from '@angular/router';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {NotificationModel} from '../../../../share/model/notification.model';
import {NewNotificationService} from './new-notification.service';
import {HttpErrorResponse} from '@angular/common/http';
import {NzMessageService} from 'ng-zorro-antd';

@Component({
  selector: 'app-new-notification',
  templateUrl: './new-notification.component.html',
  styleUrls: ['./new-notification.component.css']
})
export class NewNotificationComponent implements OnInit {

  constructor(private route: ActivatedRoute, private formBuilder: FormBuilder, private service: NewNotificationService,
              private msg: NzMessageService) {
    this.newNotificationForm = this.formBuilder.group({
      title: ['', [Validators.required]],
      content: ['', [Validators.required]],
    });
  }

  private model: NotificationModel;
  newNotificationForm: FormGroup;
  private _gTitle;

  get gTitle() {
    return this._gTitle;
  }

  set gTitle(value) {
    this._gTitle = value;
  }

  resetForm(e: MouseEvent): void {
    e.preventDefault();
    this.newNotificationForm.reset();
    // tslint:disable-next-line:forin
    for (const key in this.newNotificationForm.controls) {
      this.newNotificationForm.controls[key].markAsPristine();
      this.newNotificationForm.controls[key].updateValueAndValidity();
    }
  }

  ngOnInit(): void {
    this.route.queryParams.subscribe((params: Params) => {
      this.model = new NotificationModel('', '', params.gid);
      this.gTitle = params.title;
    });
  }

  submitFrom() {
    this.model.title = this.newNotificationForm.controls.title.value;
    this.model.content = this.newNotificationForm.controls.content.value;

    this.service.sendNotification(this.model).subscribe(
      () => {
        this.msg.success('发布成功。')
        this.newNotificationForm.reset();
      }, (error: HttpErrorResponse) => {
        this.msg.error(error.message);
        console.log(error);
      }
    );
  }
}
