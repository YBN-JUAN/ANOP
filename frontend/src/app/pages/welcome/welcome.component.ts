import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';
import {stringify} from 'querystring';

@Component({
  selector: 'app-welcome',
  templateUrl: './welcome.component.html',
  styleUrls: ['./welcome.component.css']
})
export class WelcomeComponent implements OnInit {
  selectIndex = 0;
  tabs: any[] = [
    {
      key: 'login',
      title: '登录'
    },
    {
      key: 'register',
      title: '注册'
    }
  ];
  constructor(private router:Router) {}

  ngOnInit(): void {
    this.initTab();
  }

  taoTo(tab) {
    this.router.navigateByUrl(`/welcome/${tab.key}`);
  }

  initTab() {
    // 设置再次刷新页面时还是显示之前的tab
    const key = this.router.url.substr(this.router.url.lastIndexOf('/') + 1);
    const idx = this.tabs.findIndex(w => w.key === key);
    this.selectIndex = idx;
    this.router.navigateByUrl(`/cardWhole/${this.tabs[this.selectIndex].key}`);
  }

}
