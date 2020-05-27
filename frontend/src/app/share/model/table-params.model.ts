export class TableParamsModel {
  private _data: any[];

  get data(): any[] {
    return this._data;
  }

  set data(value: any[]) {
    this._data = value;
  }

  private _loading: boolean;

  get loading(): boolean {
    return this._loading;
  }

  set loading(value: boolean) {
    this._loading = value;
  }

  private _pageSize: number;

  get pageSize(): number {
    return this._pageSize;
  }

  set pageSize(value: number) {
    this._pageSize = value;
  }

  private _pageIndex: number;

  get pageIndex(): number {
    return this._pageIndex;
  }

  set pageIndex(value: number) {
    this._pageIndex = value;
  }

  private _total: number;

  get total(): number {
    return this._total;
  }

  set total(value: number) {
    this._total = value;
  }
}
