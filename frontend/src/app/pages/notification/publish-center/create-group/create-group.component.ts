import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {CreateGroupService} from './create-group.service';
import {HttpErrorResponse} from '@angular/common/http';
import {NzMessageService} from 'ng-zorro-antd';

@Component({
  selector: 'app-create-group',
  templateUrl: './create-group.component.html',
  styleUrls: ['./create-group.component.css']
})
export class CreateGroupComponent implements OnInit {
  createGroupForm: FormGroup;

  constructor(private formBuilder: FormBuilder, private service: CreateGroupService,
              private msg: NzMessageService) {
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
    this.service.createGroupPost(title, remark, permission).subscribe(
      () => {
        this.msg.success('群组创建成功');
        this.createGroupForm.reset(); // 创建群组成功后重置表单
      }, (error: HttpErrorResponse) => {
        this.msg.error(error.message)
        console.log(error);
      }
    )
  }
}
