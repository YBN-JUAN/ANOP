export class RegisterInfoModel {
  constructor() {
  }

  private _code: string;

  set code(value: string) {
    this._code = value;
  }

  private _email: string;

  set email(value: string) {
    this._email = value;
  }

  private _password: string;

  set password(value: string) {
    this._password = value;
  }

  private _username: string;

  set username(value: string) {
    this._username = value;
  }
}

export class Email {
  email: string;
}
