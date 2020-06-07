import {Component, Input, OnInit} from '@angular/core';
import {CateInfo} from '../../../share/model/cate-info';
import {MemorandumService} from '../../../share/service/memorandum.service';
import {differenceInMinutes, format} from 'date-fns';
import {HttpErrorResponse} from '@angular/common/http';

@Component({
  selector: 'app-edit-todo',
  templateUrl: './edit-todo.component.html',
  styleUrls: ['./edit-todo.component.css']
})
export class EditTodoComponent implements OnInit {
  @Input() id: number;
  @Input() userId: number;
  @Input() isStar: boolean;
  @Input() isHeart: boolean;
  @Input() beginDate: Date;
  @Input() categoryId: number;
  @Input() content: string;
  @Input() endDate: Date;
  @Input() remindDate: Date;
  @Input() title: string;

  visible = false;
  cateList: CateInfo[];
  loading = true;
  pageIndex = 1;
  pageSize = 10;
  begin = true;
  end = true;
  remind = true;
  isEmpty = true;
  isVisible1 = false;
  isVisible2 = false;
  remindText = '';

  constructor(private service: MemorandumService) {
  }

  change(): void {
    this.visible = !this.visible;
  }

  changeStar(): void {
    this.isStar = !this.isStar;
  }

  changeHeart(): void {
    this.isHeart = !this.isHeart;
  }

  changeEmpty(): void {
    this.isEmpty = !!(this.title.match(/^\s*$/) || this.content.match(/^\s*$/));
  }

  loadDataFromServer(): void {
    this.service.getAllCate().subscribe(data => {
        this.cateList = data.list;
        this.loading = false;
      },
      (error: HttpErrorResponse) => {
        console.log(error);
      }
    );
  }

  updateTodo() {
    if (this.remindDate != null && format(new Date(), 'yyyy-MM-dd HH:mm:ss') > this.remindDate.toString()) {
      this.remindText = '请注意，您设置的邮件提醒时间已过。';
      this.isVisible2 = true;
    } else if (differenceInMinutes(this.remindDate, new Date()) <= 3) {
      this.remindText = '请注意，您设置的邮件提醒时间与当前时间相差不到3分钟，推荐邮件提醒时间与当前时间间隔不要太接近。';
      this.isVisible2 = true;
    } else if (!this.isEmpty) {
      this.service.updateTodo(
        this.beginDate == null ? null : format(this.beginDate, 'yyyy-MM-dd HH:mm'),
        this.categoryId === undefined ? null : this.categoryId,
        this.content,
        this.endDate == null ? null : format(this.endDate, 'yyyy-MM-dd HH:mm'),
        this.isStar ? 1 : 0,
        this.isHeart ? 1 : 0,
        this.remindDate == null ? null : format(this.remindDate, 'yyyy-MM-dd HH:mm'),
        this.title,
        this.id
      ).subscribe(
        () => {
          console.log(this.remindDate);
          this.service.msg.success('编辑成功成功');
          this.change();
          location.reload();
        }, (error: HttpErrorResponse) => {
          this.service.msg.error(error.error.message);
          console.log(error);
        }
      )
    } else {
      this.isVisible1 = true;
    }
  }

  deleteTodo() {
    this.service.deleteTodo(this.id).subscribe(
      () => {
        this.service.msg.success('删除成功');
        location.reload();
      }, (error: HttpErrorResponse) => {
        this.service.msg.error(error.error.message);
        console.log(error)
      }
    )
  }

  ngOnInit(): void {
    this.loadDataFromServer();
  }
}
