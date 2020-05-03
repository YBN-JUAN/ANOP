import {TestBed} from '@angular/core/testing';

import {GroupMessageService} from './group-message.service';

describe('GroupMessageService', () => {
  let service: GroupMessageService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(GroupMessageService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
