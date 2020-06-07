import {Component, HostListener, OnInit} from '@angular/core';

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
      title: '历史待办事项',
      icon: 'history',
      route: 'History'
    }
  ]

  constructor() {
  }

  ngOnInit(): void {
  }

  // 监听窗口宽度，小于一定数值自动折叠侧边菜单
  @HostListener('window:resize', ['$event'])
  onResize(event) {
    const trigger = document.getElementById('trigger');
    trigger.hidden = event.target.innerWidth < 850;
    this.isCollapsed = event.target.innerWidth < 850;
  }
}
