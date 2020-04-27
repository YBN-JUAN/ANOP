import { Component, OnInit } from '@angular/core';
import {NzTableQueryParams} from 'ng-zorro-antd';
import {UserRequest} from '../../../../share/model/user-request';
import {UserRequestService} from '../../../../share/service/user-request.service';

@Component({
  selector: 'app-request-list',
  templateUrl: './request-list.component.html',
  styleUrls: ['./request-list.component.css']
})
export class RequestListComponent implements OnInit {
  total = 1;
  requestList: UserRequest[] = [];
  loading = true;
  pageSize = 10;
  pageIndex = 1;
  buttonTitle = "切换到我管理的群组的用户请求";
  listType = 0;

  constructor(private service: UserRequestService) { }

  loadDataFromServer(
    pageIndex: number,
    pageSize: number,
  ): void {
    this.loading = true;
    this.service.getUserRequest(this.listType,"id", pageIndex, pageSize).subscribe(data => {
        this.loading = false;
        this.total = 200; // mock the total data here
        this.requestList = data.list;
        console.log(this.requestList);
        console.log(data);
      },
      error => { console.log(error); }
    );
  }

  onQueryParamsChange(params: NzTableQueryParams): void {
    console.log(params);
    const { pageSize, pageIndex} = params;
    this.loadDataFromServer(pageIndex, pageSize);
  }

  changeList() {
    if (this.listType == 0) {
      this.buttonTitle = "切换到我创建的群组的用户请求";
      this.listType = 1;
    } else {
      this.buttonTitle = "切换到我管理的群组的用户请求";
      this.listType = 0;
    }
    this.pageIndex = 1;
    this.loadDataFromServer(this.pageIndex, this.pageSize);
  }

  ngOnInit(): void {
    this.loadDataFromServer(this.pageIndex, this.pageSize);
  }
}
