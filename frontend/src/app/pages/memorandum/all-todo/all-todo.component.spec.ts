import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AllTodoComponent } from './all-todo.component';

describe('AllTodoComponent', () => {
  let component: AllTodoComponent;
  let fixture: ComponentFixture<AllTodoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AllTodoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AllTodoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
