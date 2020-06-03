export class TableParamsModel<T> {
  constructor(loading: boolean, pageSize: number, pageIndex: number) {
    this._loading = loading;
    this._pageSize = pageSize;
    this._pageIndex = pageIndex;
  }

  private _data: T[];

  get data(): T[] {
    return this._data;
  }

  set data(value: T[]) {
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

  setTable(data: T[], total: number, loading: boolean) {
    this.data = data;
    this.total = total;
    this.loading = loading;
  }
}
