import { Component, OnInit } from '@angular/core';
import { NzTableQueryParams } from 'ng-zorro-antd/table';
import {Group} from '../../../../share/model/group-info';
import {PublishCenterService} from '../../../../share/service/publish-center.service';

@Component({
  selector: 'app-publish-center-group-list',
  templateUrl: './publish-center-group-list.component.html'
})

export class PublishCenterGroupListComponent implements OnInit {
  total = 1;
  listOfGroups: Group[] = [];
  loading = true;
  pageSize = 10;
  pageIndex = 1;

  loadDataFromServer(
    pageIndex: number,
    pageSize: number,
  ): void {
    this.loading = true;
    this.service.getGroups("id", pageIndex, pageSize).subscribe(data => {
      this.loading = false;
      this.total = 200; // mock the total data here
      this.listOfGroups = data.list;
      console.log(this.listOfGroups);
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

  deletGroup() {

  }

  constructor(private service: PublishCenterService) {}

  ngOnInit(): void {
    this.loadDataFromServer(this.pageIndex, this.pageSize);
    console.log(JSON.stringify(this.listOfGroups));
  }
}
