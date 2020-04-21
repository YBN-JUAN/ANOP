import {Component, OnInit} from '@angular/core';
import {AuthService} from '../../../share/service/auth.service';
import {HttpClient} from '@angular/common/http';
import {Router} from '@angular/router';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {UserCenterService} from '../../../share/service/user-center.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  validateForm: FormGroup;
  error: boolean = false;
  errMsg: string;

  constructor(private fb: FormBuilder,
              private http: HttpClient,
              private router: Router,
              private app: AuthService,
              private userCenterService: UserCenterService) {
  }

  ngOnInit(): void {
    this.validateForm = this.fb.group({
      username: [null, [Validators.required]],
      password: [null, [Validators.required]],
    });
  }

  submitForm(): void {
    // if (!this.validateForm.valid) {
    //   this.openDirtyControl(this.validateForm);
    // } else {
    //   this.app.authenticate<object>(this.validateForm.value, () => {
           this.router.navigateByUrl('/notification');
    //       this.userCenterService.storageUser();
    //     },
    //     (result) => {
    //       this.error = true;
    //       alert(result.message);
    //       this.errMsg = result.message;
    //     });
    // }
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
