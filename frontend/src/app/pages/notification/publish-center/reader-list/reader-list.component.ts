import {Component, Input, OnInit} from '@angular/core';
import {GroupUser} from '../../../../share/model/user-info.model';
import {PublishCenterService} from '../../../../share/service/publish-center.service';
import {HttpErrorResponse} from '@angular/common/http';

@Component({
  selector: 'app-reader-list',
  templateUrl: './reader-list.component.html',
  styleUrls: ['./reader-list.component.css']
})
export class ReaderListComponent implements OnInit {
  readList: GroupUser[] = [];
  unreadList: GroupUser[] = [];
  @Input() service: PublishCenterService;
  @Input() gid: number;
  @Input() nid: number;

  constructor() {
  }

  ngOnInit(): void {
    this.setList();
    console.log(this.nid)
    console.log(this.readList)
  }

  setList() {
    this.service.getNotificationReaders(this.gid, this.nid).subscribe(
      readerList => {
        readerList.forEach(reader => {
          if (reader.isRead === 1) {
            this.readList.push(reader);
          } else {
            this.unreadList.push(reader);
          }
        });
      }, (error: HttpErrorResponse) => {
        this.service.msg.error(error.error.message);
      });
  }
}
