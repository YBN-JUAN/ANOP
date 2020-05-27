import { Component, OnInit } from '@angular/core';
import { UserCenterService } from '../../../share/service/user-center.service';
import { UserInfoModel } from '../../../share/model/user-info.model';
import { UploadFile } from 'ng-zorro-antd/upload';
import { Observable, Observer } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { NzMessageService } from 'ng-zorro-antd';

@Component({
  selector: 'app-edit-profile',
  templateUrl: './edit-profile.component.html',
  styleUrls: ['./edit-profile.component.css']
})
export class EditProfileComponent implements OnInit {
  public user: UserInfoModel = {
    id: 0,
    email: '',
    userName: '',
    nickName: '',
    creationTime: '',
    avatarUrl: '',
  };
  public avatarURL = '';
  public loading = false;
  public fileList = [];
  public httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': undefined
    })
  };

  constructor(
    public service:UserCenterService,
    private msg: NzMessageService,
    private http: HttpClient
  ) { }

  ngOnInit(): void {
    this.service.getConfig().subscribe(data => {
      this.user = data;
      var avatar = data.avatarUrl;
      if(avatar.startsWith('https://')) {
        this.user.avatarUrl = data.avatarUrl;
      }
      else {
        this.user.avatarUrl = 'http://' + data.avatarUrl;
      }
    });
  }

  onEditUsername() {
    document.getElementById('show').style.display='none';
    document.getElementById('hide').style.display='block';
  }

  onSubmit() {
    document.getElementById('hide').style.display='none';
    document.getElementById('show').style.display='block';
    this.service.updateUserInfo(this.user.nickName, this.user.avatarUrl);
  }

  onUrlUpload() {
    document.getElementById('upload-show').style.display='none';
    document.getElementById('upload-hide').style.display='block';
  }

  getAvatarUrl() {
    document.getElementById('upload-hide').style.display='none';
    document.getElementById('upload-show').style.display='block';
    this.service.updateUserInfo(this.user.nickName, this.avatarURL);
    this.service.getConfig().subscribe(data => {
      this.user.avatarUrl = data.avatarUrl;
    });
    location.reload();
  }

  beforeUpload = (file: File) => {
    return new Observable((observer: Observer<boolean>) => {
      const isJpgOrPng = file.type === 'image/jpeg' || file.type === 'image/png';
      if (!isJpgOrPng) {
        this.msg.error('头像只能为jpg/png格式的图片！');
        observer.complete();
        return;
      }
      const isLt8M = file.size / 1024 / 1024 < 8;
      if (!isLt8M) {
        this.msg.error('图像必须小于8MB！');
        observer.complete();
        return;
      }
      observer.next(isJpgOrPng && isLt8M);
      observer.complete();
    });
  };

  private getBase64(img: File, callback: (img: string) => void): void {
    const reader = new FileReader();
    reader.addEventListener('load', () => callback(reader.result!.toString()));
    reader.readAsDataURL(img);
  }

  handleChange(info: { file: UploadFile }): void {
    let file = this.fileList[0]
    this.upload(file);
    switch (info.file.status) {
      case 'uploading':
        this.loading = true;
        break;
      case 'done':
        this.getBase64(info.file!.originFileObj!, (img: string) => {
          this.loading = false;
          this.user.avatarUrl = img;
          window.alert("修改成功！");
          location.reload();
        });
        break;
      case 'error':
        this.msg.error('上传失败！');
        this.loading = false;
        break;
    }
  }

  upload(img: File) {
    let formData = new FormData();
    formData.append("avatarimg", img);
    this.http
      .post("http://localhost:8080/v1/usr/avatar", formData, this.httpOptions)
      .subscribe(
        file => {
          console.log(file);
        },
        error => {
          console.log(error);
        },
        () => {
          location.reload();
        }
      );
  }
}
