import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-user-center',
  templateUrl: './user-center.component.html',
  styleUrls: ['./user-center.component.css']
})
export class UserCenterComponent implements OnInit {
  isCollapsed = false;

  constructor() { }

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
