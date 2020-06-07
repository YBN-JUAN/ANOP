import {Component, HostListener, OnInit} from '@angular/core';

@Component({
  selector: 'app-notification',
  templateUrl: './notification.component.html',
  styleUrls: ['./notification.component.css']
})
export class NotificationComponent implements OnInit {

  openMap: { [name: string]: boolean } = {
    sub1: false,
    sub2: false,
  };
  isCollapsed = false;

  constructor() {
  }

  ngOnInit(): void {
  }

  openHandler(value: string): void {
    for (const key in this.openMap) {
      if (key !== value) {
        this.openMap[key] = false;
      }
    }
  }

  setCollapsed() {
    this.isCollapsed = !this.isCollapsed;
    const logo = document.getElementById('logo');
    if (this.isCollapsed) {
      logo.style.paddingLeft = '2px';
    } else {
      logo.style.paddingLeft = 'unset';
    }
  }

  // 监听窗口宽度，小于一定数值自动折叠侧边菜单
  @HostListener('window:resize', ['$event'])
  onResize(event) {
    const trigger = document.getElementById('trigger');
    trigger.hidden = event.target.innerWidth < 850;
    this.isCollapsed = event.target.innerWidth < 850;
  }
}
