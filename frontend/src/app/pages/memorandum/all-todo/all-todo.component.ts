import { Component, OnInit } from '@angular/core';
import {TodoInfo} from '../../../share/model/todo-Info';
import {MemorandumService} from '../../../share/service/memorandum.service';

@Component({
  selector: 'app-all-todo',
  templateUrl: './all-todo.component.html',
  styleUrls: ['./all-todo.component.css']
})
export class AllTodoComponent implements OnInit {

  todoList: TodoInfo[];
  loading = true;
  pageSize = 10;
  pageIndex = 1;
  total: number;
  inputValue = '';
  constructor(private service: MemorandumService) { }

  loadDataFromServer(): void {
    this.service.getTodoList(0,'ASC', this.pageIndex, this.pageSize, this.inputValue).subscribe(data => {
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
