import { Component, OnInit } from '@angular/core';
import {GroupUpdateInfo, UpdateUserInfo} from '../../../../share/model/group-info.model';
import {GroupUser} from '../../../../share/model/user-info.model';
import {ActivatedRoute, Router} from '@angular/router';
import {PublishCenterService} from '../../../../share/service/publish-center.service';
import {NzTableQueryParams, toNumber} from 'ng-zorro-antd';
import {error} from 'selenium-webdriver';

@Component({
  selector: 'app-manage-group',
  templateUrl: './manage-group.component.html',
  styleUrls: ['./manage-group.component.css']
})
export class ManageGroupComponent implements OnInit {
  updateGroupInfo: GroupUpdateInfo;
  groupId: number;
  permission: string;
  total = 1;
  listOfUsers: GroupUser[] = [];
  loading = true;
  pageSize = 3;
	pageIndex = 1;
  adminInfo: UpdateUserInfo;

  constructor(private route: ActivatedRoute,
              private service: PublishCenterService,
              private router: Router) {
  }

  ngOnInit(): void {
    this.updateGroupInfo = new GroupUpdateInfo();
    this.adminInfo = new UpdateUserInfo();
    const id = toNumber(this.route.snapshot.paramMap.get('id'));
    this.groupId = Number(id);
    this.updateGroupInfo.remark = '';
    this.updateGroupInfo.title = '...';
    this.getGroupInfo(id);
    this.loadDataFromServer(this.pageIndex, this.pageSize);
  }
	
	getGroupInfo(id: number) {
    this.service.getGroup(id).subscribe(
      data => {
        this.updateGroupInfo.permission = data.permission;
        this.updateGroupInfo.title = data.title;
        this.updateGroupInfo.remark = data.remark;
        this.permission = String(data.permission);
        console.log(data);
      },
      error => {
        console.log(error);
      }
    )
  }

  loadDataFromServer(pageIndex: number, pageSize: number): void {
    this.loading = true;
    this.service.getGroupUser(this.groupId, 'id', pageIndex, pageSize).subscribe(data => {
        this.loading = false;
        this.total = data.total;
        this.listOfUsers = data.list;
        console.log(this.listOfUsers);
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

  submitForm(): void {
    this.updateGroupInfo.permission = Number(this.permission);
    //console.log(this.updateGroupInfo);
    this.service.updateGroup(this.groupId, this.updateGroupInfo, () => {
        this.router.navigateByUrl('/notification/publish');
        }
      ,
      (result) => {
      console.log(result.message);
      });
  }

  updateUser(info: GroupUser) {
    this.adminInfo.isAdmin = info.isAdmin;
    let uid = info.userId;
    this.service.updateGroupUser(this.groupId, uid, this.adminInfo).subscribe(
     data => {
       console.log("update user to admin ok");
       this.loadDataFromServer(this.pageIndex, this.pageSize);
     } ,
      error => {
       console.log(error);
      }
    );
  }

  deletUser(uid: number) {
    this.service.delGroupUser(this.groupId, uid).subscribe(
      data => {
        console.log("del user ok");
        this.loadDataFromServer(this.pageIndex, this.pageSize);
      },
      error => {
        console.log(error);
      }
    )
  }
}
