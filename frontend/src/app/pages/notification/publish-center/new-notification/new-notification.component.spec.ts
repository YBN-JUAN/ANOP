import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { NewNotificationComponent } from './new-notification.component';

describe('NewNotificationComponent', () => {
  let component: NewNotificationComponent;
  let fixture: ComponentFixture<NewNotificationComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ NewNotificationComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NewNotificationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
