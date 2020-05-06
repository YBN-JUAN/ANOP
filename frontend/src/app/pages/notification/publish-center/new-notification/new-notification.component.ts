import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Params} from '@angular/router';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {NotificationModel} from '../../../../share/model/notification.model';
import {NewNotificationService} from './new-notification.service';
import {HttpErrorResponse} from '@angular/common/http';

@Component({
  selector: 'app-new-notification',
  templateUrl: './new-notification.component.html',
  styleUrls: ['./new-notification.component.css']
})
export class NewNotificationComponent implements OnInit {

  newNotificationForm: FormGroup;
  private gid;

  constructor(private route: ActivatedRoute, private formBuilder: FormBuilder, private service: NewNotificationService) {
    this.newNotificationForm = this.formBuilder.group({
      title: ['', [Validators.required]],
      content: ['', [Validators.required]],
    });
  }

  ngOnInit(): void {
    this.route.queryParams.subscribe((params: Params) => {
      this.gid = params.gid;
    });
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

  submitFrom() {
    const title = this.newNotificationForm.controls.title.value;
    const content = this.newNotificationForm.controls.content.value;

    this.service.sendNotification(new NotificationModel(title, content, this.gid)).subscribe(
      (response) => {
        console.log(response);
        alert('发布成功。');
      }, (error: HttpErrorResponse) => {
        console.log(error);
      }
    );
    console.log(new NotificationModel(title, content, this.gid));
  }
}
