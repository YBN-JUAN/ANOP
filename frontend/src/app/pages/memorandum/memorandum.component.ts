import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-memorandum',
  templateUrl: './memorandum.component.html',
  styleUrls: ['./memorandum.component.css']
})
export class MemorandumComponent implements OnInit {
  isCollapsed = false;
  collapsedItems=[
    {
      title:'待办事项',
      icon:'profile',
      route:'all'
    },
    {
      title:'重要待办',
      icon:'heart',
      route:'#'
    },
    {
      title:'收藏',
      icon:'star',
      route:'#'
    },
    {
      title:'分类',
      icon:'group',
      route:'#'
    },
    {
      title:'历史待办事项',
      icon:'history',
      route:'#'
    }
  ]
  constructor() { }

  ngOnInit(): void {
  }

}
