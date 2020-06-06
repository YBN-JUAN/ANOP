import {Component, Input, OnInit} from '@angular/core';
import {GroupUser} from '../../../../share/model/user-info.model';
import {PublishCenterService} from '../../../../share/service/publish-center.service';
import {HttpErrorResponse} from '@angular/common/http';
import {TableParamsModel} from '../../../../share/model/table-params.model';

@Component({
  selector: 'app-reader-list',
  templateUrl: './reader-list.component.html',
  styleUrls: ['./reader-list.component.css']
})
export class ReaderListComponent implements OnInit {
  @Input() service: PublishCenterService;
  @Input() gid: number;
  @Input() nid: number;
  readTable: TableParamsModel<GroupUser> = new TableParamsModel(true, 6, 1);
  unreadTable: TableParamsModel<GroupUser> = new TableParamsModel(true, 6, 1);
  readList: GroupUser[] = [];
  unreadList: GroupUser[] = [];
  readNumTitle: string;
  unreadNumTitle: string;

  constructor() {
  }

  ngOnInit(): void {
    this.setList();
  }

  setList() {
    this.service.getNotificationReaders(this.gid, this.nid).subscribe(
      readers => {
        readers.forEach(reader => {
          if (reader.isRead === 1) {
            this.readList.push(reader);
          } else {
            this.unreadList.push(reader);
          }
        });
        this.loadTableData(this.readTable, this.readList);
        this.loadTableData(this.unreadTable, this.unreadList);
      }, (error: HttpErrorResponse) => {
        this.service.msg.error(error.error.message);
      });
  }

  loadTableData(table: TableParamsModel<any>, list: any[]) {
    table.loading = true;
    // 起始索引，比如第1页的起始是(1 - 1) * pageSize = 0，第2页的起始是(2 - 1) * pageSize = pageSize
    const start = (table.pageIndex - 1) * table.pageSize
    let end: number;
    if (table.pageSize * table.pageIndex <= list.length) {
      // total正好是pageSize的整数倍，则start + pageSize = end
      end = start + table.pageSize;
    } else {
      end = start + list.length % table.pageSize + 1;
    }
    table.setTable(list.slice(start, end), list.length, false);
  }
}
