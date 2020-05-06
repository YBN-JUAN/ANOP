import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {NewNotificationService} from '../new-notification/new-notification.service';

@Component({
  selector: 'app-create-group',
  templateUrl: './create-group.component.html',
  styleUrls: ['./create-group.component.css']
})
export class CreateGroupComponent implements OnInit {
  createGroupForm: FormGroup;

  constructor(private formBuilder: FormBuilder, private service: NewNotificationService) {
    this.createGroupForm = this.formBuilder.group({
      title: ['', [Validators.required]],
      remark: ['', null],
      permission: ['', [Validators.required]]
    });
  }

  ngOnInit(): void {
  }

  resetForm(e: MouseEvent): void {
    e.preventDefault();
    this.createGroupForm.reset();
    // tslint:disable-next-line:forin
    for (const key in this.createGroupForm.controls) {
      this.createGroupForm.controls[key].markAsPristine();
      this.createGroupForm.controls[key].updateValueAndValidity();
    }
  }

  submitFrom() {
    const title = this.createGroupForm.controls.title.value;
    const remark = this.createGroupForm.controls.remark.value;
    const permission = this.createGroupForm.controls.permission.value;
    console.log(permission)
  }
}
