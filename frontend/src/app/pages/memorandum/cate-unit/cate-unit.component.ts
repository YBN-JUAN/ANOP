import {Component, Input, OnInit} from '@angular/core';
import {MemorandumService} from '../../../share/service/memorandum.service';
import {Router} from '@angular/router';
import {HttpErrorResponse} from '@angular/common/http';

@Component({
  selector: 'app-cate-unit',
  templateUrl: './cate-unit.component.html',
  styleUrls: ['./cate-unit.component.css']
})
export class CateUnitComponent implements OnInit {
  @Input() typeName: string;
  @Input() todoNum: number;
  @Input() id: number;

  edit = true;
  numText = '待办事项数';
  isVisible = false;

  constructor(private service: MemorandumService, public router: Router) {
  }

  changeEditable() {
    this.edit = false;
  }

  updateCate() {
    this.service.updateCate(this.id, this.typeName).subscribe(
      () => {
        this.service.msg.success('修改分类信息成功');
        this.edit = true;
      }, (error: HttpErrorResponse) => {
        this.service.msg.error(error.error.message);
      }
    )
  }

  deleteCate(){
    this.service.deleteCate(this.id).subscribe(
      () => {
        this.service.msg.success('删除分类成功');
        location.reload();
      }, (error: HttpErrorResponse) => {
        this.service.msg.error(error.error.message);
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
