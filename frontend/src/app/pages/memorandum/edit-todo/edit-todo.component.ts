import { Component, OnInit, Input } from '@angular/core';
import {CateInfo} from '../../../share/model/cate-info';
import {MemorandumService} from '../../../share/service/memorandum.service';
import {format} from 'date-fns';

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

  visible=false;
  cateList: CateInfo[];
  loading = true;
  pageIndex=1;
  pageSize=10;
  begin=true;
  end=true;
  remind=true;
  isEmpty=true;
  isVisible1=false;
  isVisible2=false;

  change(): void{
    this.visible=!this.visible;
  }

  changeStar(): void{
    this.isStar=!this.isStar;
  }

  changeHeart(): void{
    this.isHeart=!this.isHeart;
  }

  changeEmpty(): void{
    if(this.title.match(/^\s*$/)||this.content.match(/^\s*$/)){
      this.isEmpty=true;
    } else {
      this.isEmpty=false;
    }
  }
  constructor(private service: MemorandumService) { }

  loadDataFromServer(): void {
    this.service.getAllCate().subscribe(data => {
        this.cateList = data.list;
        this.loading = false;
      },
      error => { console.log(error); }
    );
  }

  updateTodo() {
    if(this.remindDate!=null&&format(new Date(),'yyyy-MM-dd HH:mm:ss')>this.remindDate.toString()){
      this.isVisible2=true;
    } else if(!this.isEmpty){
      this.service.updateTodo(
        this.beginDate===null?null:format(this.beginDate, 'yyyy-MM-dd HH:mm'),
        this.categoryId===undefined?null:this.categoryId,
        this.content,
        this.endDate===null?null:format(this.endDate, 'yyyy-MM-dd HH:mm'),
        this.isHeart?1:0,
        this.isStar?1:0,
        this.remindDate===null?null:format(this.remindDate, 'yyyy-MM-dd HH:mm'),
        this.title,
        this.id
      ).subscribe(
        data=>{
          console.log('更新待办实现成功');
          this.change();
          location.reload();
        },error => {
          console.log(error);
        }
      )
    } else {
      this.isVisible1=true;
    }
  }

  deleteTodo(){
    this.service.deleteTodo(this.id).subscribe(
      data=>{
        console.log('删除待办事项成功');
        location.reload();
      },error => {
        console.log(error)
      }
    )
  }

  ngOnInit(): void {
    this.loadDataFromServer();
  }


}
