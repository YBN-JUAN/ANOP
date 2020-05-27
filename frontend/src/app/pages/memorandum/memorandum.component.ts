import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-memorandum',
  templateUrl: './memorandum.component.html',
  styleUrls: ['./memorandum.component.css']
})
export class MemorandumComponent implements OnInit {
  isCollapsed = false;
  siderSpan=4;
  collapsedItems=[
    {
      title:'待办事项',
      icon:'profile',
      route:'all'
    },
    {
      title:'重要待办',
      icon:'heart',
      route:'Important'
    },
    {
      title:'收藏',
      icon:'star',
      route:'Favor'
    },
    {
      title:'分类',
      icon:'group',
      route:'Cate'
    },
    {
      title:'历史待办事项',
      icon:'history',
      route:'History'
    }
  ]
  constructor() { }

  ngOnInit(): void {
  }

}
