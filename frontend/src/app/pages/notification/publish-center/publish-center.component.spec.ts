import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PublishCenterComponent } from './publish-center.component';

describe('PublishCenterComponent', () => {
  let component: PublishCenterComponent;
  let fixture: ComponentFixture<PublishCenterComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PublishCenterComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PublishCenterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
