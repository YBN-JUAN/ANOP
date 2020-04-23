import { Component, OnInit } from '@angular/core';
import {TodoItemComponent} from '../todo-item/todo-item.component';

@Component({
  selector: 'app-all-todo',
  templateUrl: './all-todo.component.html',
  styleUrls: ['./all-todo.component.css']
})
export class AllTodoComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }

}
