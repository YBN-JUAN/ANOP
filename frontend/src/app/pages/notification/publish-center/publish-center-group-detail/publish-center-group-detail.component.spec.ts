import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PublishCenterGroupDetailComponent } from './publish-center-group-detail.component';

describe('PublishCenterGroupDetailComponent', () => {
  let component: PublishCenterGroupDetailComponent;
  let fixture: ComponentFixture<PublishCenterGroupDetailComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PublishCenterGroupDetailComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PublishCenterGroupDetailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
