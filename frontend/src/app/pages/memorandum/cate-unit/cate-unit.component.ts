import { Component, OnInit, Input } from '@angular/core';
import {MemorandumService} from '../../../share/service/memorandum.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-cate-unit',
  templateUrl: './cate-unit.component.html',
  styleUrls: ['./cate-unit.component.css']
})
export class CateUnitComponent implements OnInit {
  @Input() typeName:string;
  @Input() todoNum:number;
  @Input() id:number;

  edit=true;
  numText='待办事项数';
  isVisible=false;
  constructor(private service: MemorandumService,public router:Router) { }

  changeEditable(){
    this.edit=false;
  }

  updateCate(){
    this.service.updateCate(this.id, this.typeName).subscribe(
      data=>{
        console.log('修改分类信息成功');
        this.edit=true;
      },error => {
        console.log(error);
      }
    )
  }

  deleteCate(){
    this.service.deleteCate(this.id).subscribe(
      data=>{
        console.log('删除分类成功');
        location.reload();
      },error => {
        console.log(error)
      }
    )
  }

  jump(){
    console.log('路由跳转');
    this.router.navigate(['memorandum/cate-detail/',this.id])
  }
  // memorandum/Cate
  ngOnInit(): void {
  }

}
