import {Component, Input, OnInit, ViewChild} from '@angular/core';
import {format} from 'date-fns';
import {MemorandumService} from '../../../share/service/memorandum.service';
import {EditTodoComponent} from '../edit-todo/edit-todo.component';
import {HttpErrorResponse} from '@angular/common/http';

@Component({
  selector: 'app-todo-item',
  templateUrl: './todo-item.component.html',
  styleUrls: ['./todo-item.component.css']
})
export class TodoItemComponent implements OnInit {
  @Input() id: number; // 待办事项ID
  @Input() itemName: string;
  @Input() itemDeadline: Date;
  @Input() content: string;
  @Input() itemStatus: boolean;
  @Input() isStar: boolean;
  @Input() isHeart: boolean;
  @Input() userId: number;
  @Input() beginDate: Date;
  @Input() remindDate: Date;
  @Input() categoryId: number;
  @ViewChild('editTodoComponent') editTodoComponent: EditTodoComponent;
  EndDate: Date;
  BeginDate: Date;
  RemindDate: Date;
  Name = '事项名称';
  Deadline = '最终时间：';
  Status = '状态:';
  Now: string;
  isWasted: boolean;

  constructor(private service: MemorandumService) {
  }

  ngOnInit(): void {
    const date = new Date();
    const deadline = format(new Date(this.itemDeadline), 'yyyy-MM-dd HH:mm:ss');
    this.Now = format(date, 'yyyy-MM-dd HH:mm:ss');
    this.isWasted = this.Now > deadline && this.itemDeadline != null;
    this.EndDate = this.itemDeadline == null ? null : new Date(this.itemDeadline);
    this.BeginDate = this.beginDate == null ? null : new Date(this.beginDate);
    this.RemindDate = this.remindDate == null ? null : new Date(this.remindDate);
  }

  changeStar(): void {
    this.isStar = !this.isStar;
    this.service.changeChecked(2, this.id).subscribe(
      () => {
        console.log('收藏状态切换成功');
      }, (error: HttpErrorResponse) => {
        this.service.msg.error(error.error.message);
      }
    )
  }

  changeHeart(): void {
    this.isHeart = !this.isHeart;
    this.service.changeChecked(1, this.id).subscribe(
      () => {
        console.log('重要状态切换成功');
      }, (error: HttpErrorResponse) => {
        this.service.msg.error(error.error.message);
      }
    )
  }

  changeCheck(): void {
    this.itemStatus = !this.itemStatus;
    this.service.changeChecked(0, this.id).subscribe(
      () => {
        console.log('完成状态切换成功');
      }, (error: HttpErrorResponse) => {
        this.service.msg.error(error.error.message);
      }
    )
  }
}
