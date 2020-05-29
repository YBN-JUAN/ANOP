import { Component, OnInit } from '@angular/core';
import {TodoInfo} from '../../../share/model/todo-Info';
import {MemorandumService} from '../../../share/service/memorandum.service';

@Component({
  selector: 'app-imp-todo',
  templateUrl: './imp-todo.component.html',
  styleUrls: ['./imp-todo.component.css']
})
export class ImpTodoComponent implements OnInit {

  todoList: TodoInfo[];
  loading = true;
  pageSize = 10;
  pageIndex = 1;
  total: number;
  inputValue = '';
  constructor(private service: MemorandumService) { }

  loadDataFromServer(): void {
    this.loading = true;
    this.service.getTodoList(1,'ASC', this.pageIndex, this.pageSize, this.inputValue).subscribe(data => {
        this.total = data.total;
        this.todoList = data.list;
        this.loading = false;
      },
      error => { console.log(error); }
    );
  }
  ngOnInit(): void {
    this.loadDataFromServer()
  }

}
