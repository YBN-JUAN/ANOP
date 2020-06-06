// 用于发布通知的post请求体
export class NotificationModel {
  private _title: string;
  private _content: string;
  private readonly _gid: number;

  constructor(title: string, content: string, gid: number) {
    this._title = title;
    this._content = content;
    this._gid = gid;
  }

  get gid(): number {
    return this._gid;
  }

  get title(): string {
    return this._title;
  }

  set title(value: string) {
    this._title = value;
  }

  get content(): string {
    return this._content;
  }

  set content(value: string) {
    this._content = value;
  }
}
