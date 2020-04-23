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

  constructor(private service: UserRequestService) { }

  loadDataFromServer(
    pageIndex: number,
    pageSize: number,
  ): void {
    this.loading = true;
    this.service.getUserRequest("id", pageIndex, pageSize).subscribe(data => {
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

  ngOnInit(): void {
    this.loadDataFromServer(this.pageIndex, this.pageSize);
  }
}
