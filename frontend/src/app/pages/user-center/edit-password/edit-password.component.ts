import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { UserCenterService } from '../../../share/service/user-center.service';

@Component({
  selector: 'app-edit-password',
  templateUrl: './edit-password.component.html',
  styleUrls: ['./edit-password.component.css']
})
export class EditPasswordComponent implements OnInit {
  public resetForm: FormGroup;
  public errorInput = false;

  constructor(
    public fb: FormBuilder,
    public service: UserCenterService,
  ) {}

  ngOnInit(): void {
    this.resetForm = this.fb.group({
      oldPassword: [null, [Validators.required]],
      newPassword: [null, [Validators.required]],
      checkPassword: [null, [Validators.required, this.confirmationValidator]]
    });
  }

  submitForm(): void {
    for (const i in Object.keys(this.resetForm.controls)) {
      if (this.resetForm.controls.hasOwnProperty(i)) {
        this.resetForm.controls[i].markAsDirty();
        this.resetForm.controls[i].updateValueAndValidity();
      }
    }
    this.service.resetPassword(this.resetForm.controls.newPassword.value, this.resetForm.controls.oldPassword.value);
  }

  updateConfirmValidator(): void {
    Promise.resolve().then(() =>
      this.resetForm.controls.checkPassword.updateValueAndValidity());
  }

  confirmationValidator = (control: FormControl): { [s: string]: boolean } => {
    if (!control.value) {
      return {
        required: true
      };
    }
    else if (control.value !== this.resetForm.controls.newPassword.value) {
      return {
        confirm: true, error: true
      };
    }
    return {};
  };
}
