import { Component, OnInit } from '@angular/core';
import {MemorandumService} from '../../../share/service/memorandum.service';
import {CateInfo} from '../../../share/model/cate-info';

@Component({
  selector: 'app-all-cate',
  templateUrl: './all-cate.component.html',
  styleUrls: ['./all-cate.component.css']
})
export class AllCateComponent implements OnInit {
  cateList: CateInfo[];
  loading = true;
  pageSize = 10;
  pageIndex = 1;
  total: number;
  isVisible=false;
  isOkLoading=false;
  newCate: string;
  isVisible2=false;
  constructor(private service: MemorandumService) { }
  loadDataFromServer(): void {
    this.service.getCateList('ASC', this.pageIndex, this.pageSize).subscribe(data => {
        this.total = data.total;
        this.cateList = data.list;
        this.loading = false;
      },
      error => { console.log(error); }
    );
  }

  changeVisible(){
    this.isVisible=!this.isVisible;
  }

  addCate(){
    if(this.newCate===undefined||this.newCate.match(/^\s*$/)){
      this.isVisible2=true;
    } else {
      this.isOkLoading=true;
      this.service.addCate(this.newCate).subscribe(
        data=>{
          console.log('添加新分类成功');
          location.reload();
        },error => {
          console.log(error);
        }
      )
    }
  }

  ngOnInit(): void {
    this.loadDataFromServer();
  }

}
