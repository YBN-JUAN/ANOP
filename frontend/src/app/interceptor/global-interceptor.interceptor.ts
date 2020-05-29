import { Injectable } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor,
  HttpResponse,
  HttpErrorResponse
} from '@angular/common/http';
import { Observable, of, throwError } from 'rxjs';
import { mergeMap, catchError } from 'rxjs/operators';
import {Router} from '@angular/router';

@Injectable()
export class GlobalInterceptor implements HttpInterceptor {

  constructor(private cookieService:CookieService,private route: Router) {}

  intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {
    const xhr = request.clone({
      headers: request.headers
        .set('X-Requested-With', 'XMLHttpRequest')
        .set('X-XSRF-TOKEN',this.cookieService.get('XSRF-TOKEN')),
      withCredentials:true

    });
    return next.handle(xhr).pipe(mergeMap((event: any) => {
        return of(event);
      }),
      catchError((err: HttpErrorResponse) => this.handleData(err)));
  }

  private handleData(event: HttpResponse<any> | HttpErrorResponse): Observable<any> {
    if(event.status===401 && !this.route.routerState.snapshot.url.startsWith('/welcome')){
      console.log(this.route);
      this.route.navigateByUrl('/welcome/login');
    }
    return throwError(event);
  }
}
