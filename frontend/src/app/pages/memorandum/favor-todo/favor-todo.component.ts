import {Component, OnInit} from '@angular/core';
import {TodoInfo} from '../../../share/model/todo-Info';
import {MemorandumService} from '../../../share/service/memorandum.service';
import {HttpErrorResponse} from '@angular/common/http';

@Component({
  selector: 'app-favor-todo',
  templateUrl: './favor-todo.component.html',
  styleUrls: ['./favor-todo.component.css']
})
export class FavorTodoComponent implements OnInit {
  todoList: TodoInfo[];
  loading = true;
  pageSize = 10;
  pageIndex = 1;
  total: number;
  inputValue = '';
  constructor(private service: MemorandumService) { }

  loadDataFromServer(): void {
    this.loading = true;
    this.service.getTodoList(2,'ASC', this.pageIndex, this.pageSize,this.inputValue).subscribe(data => {
        this.total = data.total;
        this.todoList = data.list;
        this.loading = false;
      },
      (error: HttpErrorResponse) => {
        this.service.msg.error(error.error.message);
      }
    );
  }
  ngOnInit(): void {
    this.loadDataFromServer()
  }
}
