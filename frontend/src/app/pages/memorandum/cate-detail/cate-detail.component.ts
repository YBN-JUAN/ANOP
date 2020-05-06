import { Component, OnInit } from '@angular/core';
import {TodoInfo} from '../../../share/model/todo-Info';
import {MemorandumService} from '../../../share/service/memorandum.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-cate-detail',
  templateUrl: './cate-detail.component.html',
  styleUrls: ['./cate-detail.component.css']
})
export class CateDetailComponent implements OnInit {
  todoList: TodoInfo[];
  id:number;
  loading = true;
  pageSize = 10;
  pageIndex = 1;
  total: number;
  constructor(private service: MemorandumService, private route: ActivatedRoute) { }

  loadDataFromServer(id: number): void {
    this.service.getCate(id).subscribe(data => {
        this.total = data.total;
        this.todoList = data.list;
        this.loading = false;
        console.log('获取指定ID分类下待办事项成功');
        console.log(data.list);
        console.log(this.todoList);
      },
      error => { console.log(error); }
    );
  }

  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
      this.id = +params.get('id');
    });
    this.loadDataFromServer(this.id);
  }
}
