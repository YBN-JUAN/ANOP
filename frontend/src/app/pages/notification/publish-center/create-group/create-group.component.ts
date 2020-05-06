import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {CreateGroupService} from './create-group.service';
import {HttpErrorResponse} from '@angular/common/http';

@Component({
  selector: 'app-create-group',
  templateUrl: './create-group.component.html',
  styleUrls: ['./create-group.component.css']
})
export class CreateGroupComponent implements OnInit {
  createGroupForm: FormGroup;

  constructor(private formBuilder: FormBuilder, private service: CreateGroupService) {
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
    this.service.createGroupPost(title, remark, permission).subscribe(
      (data) => {
        console.log(data);
        alert('创建成功！');
      }, (error: HttpErrorResponse) => {
        alert('出错了，请打开控制台查看错误信息。')
        console.log(error);
      }
    )
  }
}
