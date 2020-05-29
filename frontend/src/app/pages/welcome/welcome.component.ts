import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';

@Component({
  selector: 'app-welcome',
  templateUrl: './welcome.component.html',
  styleUrls: ['./welcome.component.css']
})
export class WelcomeComponent implements OnInit {
  selectIndex = 0;
  tabs: any[] = [
    {
      key: '',
      title: ''
    },
    {
      key: 'login',
      title: '登录'
    },
    {
      key: 'register',
      title: '注册'
    },
    {
      key: '',
      title: ''
    }
  ];

  constructor(private router: Router) {
  }

  ngOnInit(): void {
    this.initTab();
  }

  taoTo(tab) {
    this.router.navigateByUrl(`/welcome/${tab.key}`).then(
      () => {
      });
  }

  initTab() {
    // 设置再次刷新页面时还是显示之前的tab
    const key = this.router.url.substr(this.router.url.lastIndexOf('/') + 1);
    this.selectIndex = this.tabs.findIndex(w => w.key === key);
    this.router.navigateByUrl(`/cardWhole/${this.tabs[this.selectIndex].key}`).then(() => {
    });
  }

}
