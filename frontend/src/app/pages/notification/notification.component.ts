import {Component, OnInit} from '@angular/core';

@Component({
  selector: 'app-notification',
  templateUrl: './notification.component.html',
  styleUrls: ['./notification.component.css']
})
export class NotificationComponent implements OnInit {
  isCollapsed = false;

  constructor() {
  }

  ngOnInit(): void {
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
}
