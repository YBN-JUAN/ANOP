import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {GroupMessageService} from './group-message.service';
import {GroupInfoModel} from '../../../../share/model/group-info.model';
import {ResponseModel} from '../../../../share/model/response.model';
import {NotificationInfoModel} from '../../../../share/model/notification-info.model';
import {HttpErrorResponse} from '@angular/common/http';

@Component({
  selector: 'app-group',
  templateUrl: './group-message.component.html',
  styleUrls: ['./group-message.component.css']
})
export class GroupMessageComponent implements OnInit {

  loading = true;

  notificationList: NotificationInfoModel[] = [];

  listOfColumn = [
    {
      title: '群组ID',
      compare: ((a, b) => a.id - b.id)
    },
    {
      title: '标题',
      compare: (a: NotificationInfoModel, b: NotificationInfoModel) => a.title.localeCompare(b.title),
      // priority: 3
    },
    {
      title: '内容',
    },
    {
      title: '发布时间',
      // compare: (a: NotificationInfoModel, b: NotificationInfoModel) => a.creationDate > b.creationDate,
      // priority: 1
    },
    {
      title: '操作'
    }
  ];

  constructor(private route: ActivatedRoute, private service: GroupMessageService) {
  }

  ngOnInit(): void {
    this.getAllMessages();
    this.loading = false;
  }

  // doGet() {
  //   const gid = toNumber(this.route.snapshot.paramMap.get('gid'));
  //   this.service.getGroupMessage(this.gid).subscribe(
  //     (data) => {
  //       console.log(data)
  //     }
  //   )
  // }

  getAllMessages() {
    this.service.getSubscribeGroups().subscribe(
      (responseModel: ResponseModel<GroupInfoModel>) => {
        for (const group of responseModel.list) {
          this.service.getGroupMessage(group.id).subscribe(
            (response: ResponseModel<NotificationInfoModel>) => {
              this.notificationList = this.notificationList.concat(response.list);
              console.log(this.notificationList)
            }, (error: HttpErrorResponse) => {
              console.log(error);
            }
          );
        }
      }, error => {
        console.log(error);
      }
    );
    this.notificationList.sort(((a, b) => a.creationDate.localeCompare(b.creationDate)))
  }
}
