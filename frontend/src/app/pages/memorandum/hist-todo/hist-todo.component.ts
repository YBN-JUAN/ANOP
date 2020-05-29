import { Component, OnInit } from '@angular/core';
import {TodoInfo} from '../../../share/model/todo-Info';
import {MemorandumService} from '../../../share/service/memorandum.service';

@Component({
  selector: 'app-hist-todo',
  templateUrl: './hist-todo.component.html',
  styleUrls: ['./hist-todo.component.css']
})
export class HistTodoComponent implements OnInit {

  todoList: TodoInfo[];
  loading = true;
  pageSize = 10;
  pageIndex = 1;
  total: number;
  inputValue = '';
  constructor(private service: MemorandumService) { }

  loadDataFromServer(): void {
    this.loading = true;
    this.service.getHistories('ASC', this.pageIndex, this.pageSize, this.inputValue).subscribe(data => {
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
