import { Injectable } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor
} from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable()
export class GlobalInterceptorInterceptor implements HttpInterceptor {

  constructor(private cookieService:CookieService) {}

  intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {
    const xhr = request.clone({
      headers: request.headers
        .set('X-Requested-With', 'XMLHttpRequest')
        .set('X-XSRF-TOKEN',this.cookieService.get('XSRF-TOKEN')),
      withCredentials:true

    });
    return next.handle(xhr);
  }
}
