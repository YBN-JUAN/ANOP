import { TestBed } from '@angular/core/testing';

import { JoinGroupService } from './join-group.service';

describe('JoinGroupService', () => {
  let service: JoinGroupService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(JoinGroupService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
