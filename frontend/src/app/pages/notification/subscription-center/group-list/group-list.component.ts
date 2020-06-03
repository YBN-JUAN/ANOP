import {Component, OnInit} from '@angular/core';
import {GroupInfoModel} from '../../../../share/model/group-info.model';
import {NzTableQueryParams} from 'ng-zorro-antd';
import {SubscriptionCenterService} from '../../../../share/service/subscription-center.service';
import {TableParamsModel} from '../../../../share/model/table-params.model';
import {ResponseModel} from '../../../../share/model/response.model';

@Component({
  selector: 'app-group-list',
  templateUrl: './group-list.component.html',
  styleUrls: ['./group-list.component.css']
})
export class GroupListComponent implements OnInit {

  groupTable: TableParamsModel<GroupInfoModel> = new TableParamsModel(true, 6, 1);

  loadDataFromServer(pageIndex: number, pageSize: number): void {
    this.groupTable.loading = true;
    this.service.getGroups('id', pageIndex, pageSize).subscribe(
      (response: ResponseModel<GroupInfoModel>) => {
        this.groupTable.setTable(response.list, response.total, false);
        this.groupTable.data.forEach(item => {
          // 为每个群组获取未读通知数量
          this.service.getUnreadCount(item.id).subscribe(
            data => {
              item.unreadCount = data.unreadCount;
            }, err => {
              console.log(err);
            }
          );
        });
        console.log(this.groupTable.data);
      }, error => {
        console.log(error);
      }
    );
  }

  onQueryParamsChange(params: NzTableQueryParams): void {
    const {pageSize, pageIndex} = params;
    this.loadDataFromServer(pageIndex, pageSize);
  }

  constructor(private service: SubscriptionCenterService) {
  }

  ngOnInit(): void {
    this.loadDataFromServer(this.groupTable.pageIndex, this.groupTable.pageSize);
  }
}
