import {Component, OnInit} from '@angular/core';
import {toNumber} from "ng-zorro-antd";
import {ActivatedRoute} from "@angular/router";
import {GroupMessageService} from "./group-message.service";
import {GroupInfoModel} from "../../../../share/model/group-info.model";

@Component({
  selector: 'app-group',
  templateUrl: './group-message.component.html',
  styleUrls: ['./group-message.component.css']
})
export class GroupMessageComponent implements OnInit {
  public gid;

  constructor(private route: ActivatedRoute, private service: GroupMessageService) {
  }

  ngOnInit(): void {
  }

  doGet() {
    const gid = toNumber(this.route.snapshot.paramMap.get('gid'));
    this.service.getGroupMessage(this.gid).subscribe(
      (data) => {
        console.log(data)
      }
    )
  }

  getAllMessages() {
    this.service.getSubscribeGroups().subscribe(
      (groups: GroupInfoModel[]) => {
        for (const group in groups) {

        }
      }
    )
  }
}
