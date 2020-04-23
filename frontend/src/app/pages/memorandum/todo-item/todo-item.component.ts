import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-todo-item',
  templateUrl: './todo-item.component.html',
  styleUrls: ['./todo-item.component.css']
})
export class TodoItemComponent implements OnInit {
  checked=false;
  Name='事项名称';
  itemName='内容概要';
  Author='发布者:';
  itemAuthor='张三';
  Deadline='最终时间：';
  itemDeadline='2020-3-22 12:00';
  Status='状态:';
  itemStatus='未完成';
  isStar=false;
  isHeart=false;
  constructor() { }

  ngOnInit(): void {
  }
  changeStar(): void{
    this.isStar=!this.isStar
  }
  changeHeart(): void{
    this.isHeart=!this.isHeart
  }
}
