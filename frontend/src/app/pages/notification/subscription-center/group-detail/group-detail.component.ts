import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {Group} from '../../../../share/model/group-info';
import {SubscriptionCenterService} from '../../../../share/service/subscription-center.service';

@Component({
  selector: 'app-group-detail',
  templateUrl: './group-detail.component.html',
  styleUrls: ['./group-detail.component.css']
})
export class GroupDetailComponent implements OnInit {
  public group: Group;
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
}
