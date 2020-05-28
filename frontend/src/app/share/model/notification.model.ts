// 用于发布通知的post请求体
export class NotificationModel {
  private readonly _title: string;
  private readonly _content: string;
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

  get content(): string {
    return this._content;
  }
}
