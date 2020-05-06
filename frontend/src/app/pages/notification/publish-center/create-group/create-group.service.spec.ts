import {TestBed} from '@angular/core/testing';

import {CreateGroupService} from './create-group.service';

describe('CreateGroupService', () => {
  let service: CreateGroupService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CreateGroupService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
