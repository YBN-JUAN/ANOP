import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {Group} from '../../../../share/model/group-info';
import {SubscriptionCenterService} from '../../../../share/service/subscription-center.service';
import {NzTableQueryParams} from 'ng-zorro-antd';
import {GroupUser} from '../../../../share/model/user-info';

@Component({
  selector: 'app-group-detail',
  templateUrl: './group-detail.component.html',
  styleUrls: ['./group-detail.component.css']
})

export class GroupDetailComponent implements OnInit {
  public group: Group;
  total = 1;
  listOfUsers: GroupUser[] = [];
  loading = true;
  pageSize = 5;
  pageIndex = 1;
  constructor(private route: ActivatedRoute,
              private service: SubscriptionCenterService) {
  }

  ngOnInit(): void {
    this.group = new Group();
    const id = +this.route.snapshot.paramMap.get('id');
    this.group.remark = "";
    this.group.id = id;
    this.group.title = "...";
    this.group.creationDate = "...";
    this.group.avatarUrl = "...";
    this.getGroupInfo(id);
    this.loadDataFromServer(this.pageIndex, this.pageSize);
  }

  getGroupInfo(id:number) {
    this.service.getGroup(id).subscribe(
      data => {
        this.group = data;
        console.log(data)
        console.log(this.group);
      },
      error => {
        console.log(error);
      }
    )
  }


  loadDataFromServer(
    pageIndex: number,
    pageSize: number,
  ): void {
    this.loading = true;
    this.service.getGroupUser(this.group.id,"id", pageIndex, pageSize).subscribe(data => {
        this.loading = false;
        this.total = 200; // mock the total data here
        this.listOfUsers = data.list;
        console.log(this.listOfUsers);
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
}
