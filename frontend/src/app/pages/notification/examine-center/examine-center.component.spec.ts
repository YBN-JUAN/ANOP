import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ExamineCenterComponent } from './examine-center.component';

describe('ExamineCenterComponent', () => {
  let component: ExamineCenterComponent;
  let fixture: ComponentFixture<ExamineCenterComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ExamineCenterComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ExamineCenterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
