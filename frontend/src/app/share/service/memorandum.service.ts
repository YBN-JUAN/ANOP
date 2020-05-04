import { Injectable } from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import {ResponseModel} from '../model/response.model';
import {TodoInfo} from '../model/todo-Info';
import {CateInfo} from '../model/cate-info';

@Injectable({
  providedIn: 'root'
})
export class MemorandumService {

  public todoUrl = 'http://localhost:8080/v1/todos';
  public cateUrl = 'http://localhost:8080/v1/categories';

  // 获取待办事项
  getTodoList(
    flag: number,
    orderBy: string,
    pageNum: number,
    pageSize: number
  ) {
    const params = new HttpParams()
      .append('flag', `${flag}`)
      .append('orderBy', orderBy)
      .append('pageNum', `${pageNum}`)
      .append('pageSize', `${pageSize}`);
    return this.http.get<ResponseModel<TodoInfo>>(`${this.todoUrl}`, { params });
  }

  // 切换对应待办事项的对应状态
  changeChecked(flag: number,id: number){
    const params = new HttpParams()
      .append('flag', `${flag}`)
    return this.http.put(`${this.todoUrl}`+'/check/'+`${id}`,'',{ params });
  }

  // 新建待办事项
  addTodo(
  beginDate: string,
  categoryId: number,
  content: string,
  endDate: string,
  isFavorite: number,
  isImportant: number,
  remindDate: string,
  title: string
  ){
    return this.http.post(`${this.todoUrl}`,{
      'beginDate': beginDate,
      'categoryId': categoryId,
      'content': content,
      'endDate': endDate,
      'isFavorite': isFavorite,
      'isImportant': isImportant,
      'remindDate': remindDate,
      'title': title
    });
  }

  // 获取指定ID的待办事项
  getTodo(id: number){
    return this.http.get<TodoInfo>(`${this.todoUrl}`+`${id}`);
  }

  // 更新对应ID待办事项
  updateTodo(
    beginDate: string,
    categoryId: number,
    content: string,
    endDate: string,
    isFavorite: number,
    isImportant: number,
    remindDate: string,
    title: string,
    id: number
  ){
    return this.http.put(`${this.todoUrl}`+'/'+`${id}`,{
      'beginDate': beginDate,
      'categoryId': categoryId,
      'content': content,
      'endDate': endDate,
      'isFavorite': isFavorite,
      'isImportant': isImportant,
      'remindDate': remindDate,
      'title': title,
      'id': id
    });
  }

  // 删除对应ID待办事项
  deleteTodo(id:number) {
    return this.http.delete(`${this.todoUrl}`+'/'+`${id}`);
  }

  // 获取历史待办事项
  getHistories(
    orderBy: string,
    pageNum: number,
    pageSize: number
  ){
    const params = new HttpParams()
      .append('orderBy', orderBy)
      .append('pageNum', `${pageNum}`)
      .append('pageSize', `${pageSize}`);
    return this.http.get<ResponseModel<TodoInfo>>(`${this.todoUrl}`+'/histories', { params });
  }

  // 获取分类
  getCateList(
    orderBy: string,
    pageNum: number,
    pageSize: number
  ) {
    const params = new HttpParams()
      .append('orderBy', orderBy)
      .append('pageNum', `${pageNum}`)
      .append('pageSize', `${pageSize}`);
    return this.http.get<ResponseModel<CateInfo>>(`${this.cateUrl}`, { params });
  }

  // 修改对应ID的分类名称
  updateCate(id: number,typeName: string){
    return this.http.put(`${this.cateUrl}`+'/'+`${id}`,{ 'typeName': typeName },);
  }

  // 删除对应ID的分类
  deleteCate(id:number){
    return this.http.delete(`${this.cateUrl}`+'/'+`${id}`);
  }

  // 添加新的分类
  addCate(typeName:string){
    return this.http.post(`${this.cateUrl}`,{'typeName':typeName});
  }

  // 获取对应ID分类下所有待办事项的信息
  getCate(id:number){
    return this.http.get<ResponseModel<TodoInfo>>(`${this.cateUrl}`+'/list/'+`${id}`);
  }

  // 获取所有分类
  getAllCate() {
    return this.http.get<ResponseModel<CateInfo>>(`${this.cateUrl}`);
  }
  constructor(private http: HttpClient) { }
}
