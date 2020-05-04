import {Component, OnInit} from '@angular/core';
import {GroupInfoModel} from '../../../../share/model/group-info.model';
import {NzTableQueryParams} from 'ng-zorro-antd';
import {SubscriptionCenterService} from '../../../../share/service/subscription-center.service';

@Component({
  selector: 'app-group-list',
  templateUrl: './group-list.component.html',
  styleUrls: ['./group-list.component.css']
})
export class GroupListComponent implements OnInit {
  total = 1;
  listOfGroups: GroupInfoModel[] = [];
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
      error => {
        console.log(error);
      }
    );
  }

  onQueryParamsChange(params: NzTableQueryParams): void {
    console.log(params);
    const {pageSize, pageIndex} = params;
    this.loadDataFromServer(pageIndex, pageSize);
  }

  constructor(private service: SubscriptionCenterService) {
  }

  ngOnInit(): void {
    this.loadDataFromServer(this.pageIndex, this.pageSize);
  }

}
