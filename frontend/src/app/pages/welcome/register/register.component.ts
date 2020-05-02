import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {HttpClient} from '@angular/common/http';
import {Router} from '@angular/router';
import {AuthService} from '../../../share/service/auth.service';
import {UserCenterService} from '../../../share/service/user-center.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  validateForm: FormGroup;
  error: boolean = false;
  errMsg:string;

  constructor(private fb: FormBuilder,
              private http:HttpClient,
              private router: Router,
              private app: AuthService,
              private userCenterService: UserCenterService) {}

  ngOnInit(): void {
    this.validateForm = this.fb.group({
      username: [null, [Validators.required]],
      email: [null, [Validators.required]],
      code: [null, [Validators.required]],
      password: [null, [Validators.required]],
    });
  }

  submitForm(): void {
    if (!this.validateForm.valid) {
      this.openDirtyControl(this.validateForm);
    }
  }

  // 打开脏检验
  openDirtyControl(data) {
    for (const i in data.controls) {
      this.validateForm.controls[i].markAsDirty();
      this.validateForm.controls[i].updateValueAndValidity();
    }
  }

  // 关闭脏校验
  closeDirtyControl(data) {
    for (const i in data.controls) {
      this.validateForm.controls[i].clearValidators();
      this.validateForm.controls[i].updateValueAndValidity();
    }
  }
}
