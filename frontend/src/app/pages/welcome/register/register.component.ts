import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {Router} from '@angular/router';
import {Email, RegisterInfo} from '../../../share/model/register-info.model';
import {RegisterService} from '../../../share/service/register.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  validateForm: FormGroup;
  error = false;
  errorMsg = '';

  constructor(private fb: FormBuilder,
              private service: RegisterService,
              private route: Router) {
  }

  ngOnInit(): void {
    this.validateForm = this.fb.group({
      username: [null, [Validators.required]],
      email: [null, [Validators.email, Validators.required]],
      code: [null, [Validators.required]],
      password: [null, [Validators.required]],
      confirm: [null, [this.confirmValidator]]
    });
  }

  confirmValidator = (control: FormControl): { [s: string]: boolean } => {
    if (!control.value) {
      return { error: true, required: true };
    } else if (control.value !== this.validateForm.controls.password.value) {
      return { confirm: true, error: true };
    }
    return {};
  };

  submitForm(): void {
    if (!this.validateForm.valid) {
      this.openDirtyControl(this.validateForm);
    } else {
      const info = new RegisterInfo();
      info.code = this.validateForm.controls.code.value;
      info.email = this.validateForm.controls.email.value;
      info.password = this.validateForm.controls.password.value;
      info.username = this.validateForm.controls.username.value;
      console.log(info);
      this.service.register(info).subscribe(
        data => {
          this.error = false;
          this.route.navigateByUrl('/welcome/login');
        },
        error => {
          this.error = true;
          this.errorMsg = '注册失败';
          console.log(error);
        }
      )
    }
  }

  getCode() {
    const body = new Email();
    if (this.validateForm.controls.email.valid) {
      body.email = this.validateForm.controls.email.value;
      this.service.sendCode(body).subscribe(
        data => {
          this.error = false;
          window.alert('验证码已经发送');
        },
        error => {
          this.error = true;
          this.errorMsg = '验证码发送失败';
          console.log(error);
        }
      )
    } else {
      this.error = true;
      this.errorMsg = '邮箱地址不合法';
    }
  }

  // 打开脏检验
  openDirtyControl(data) {
    // tslint:disable-next-line:forin
    for (const i in data.controls) {
      this.validateForm.controls[i].markAsDirty();
      this.validateForm.controls[i].updateValueAndValidity();
    }
  }

  // 关闭脏校验
  closeDirtyControl(data) {
    // tslint:disable-next-line:forin
    for (const i in data.controls) {
      this.validateForm.controls[i].clearValidators();
      this.validateForm.controls[i].updateValueAndValidity();
    }
  }
}
