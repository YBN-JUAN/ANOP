import { TestBed } from '@angular/core/testing';

import { GlobalInterceptorInterceptor } from './global-interceptor.interceptor';

describe('GlobalInterceptorInterceptor', () => {
  beforeEach(() => TestBed.configureTestingModule({
    providers: [
      GlobalInterceptorInterceptor
      ]
  }));

  it('should be created', () => {
    const interceptor: GlobalInterceptorInterceptor = TestBed.inject(GlobalInterceptorInterceptor);
    expect(interceptor).toBeTruthy();
  });
});
