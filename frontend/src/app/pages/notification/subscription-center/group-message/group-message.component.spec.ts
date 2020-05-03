import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { GroupMessageComponent } from './group-message.component';

describe('GroupComponent', () => {
  let component: GroupMessageComponent;
  let fixture: ComponentFixture<GroupMessageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ GroupMessageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GroupMessageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
