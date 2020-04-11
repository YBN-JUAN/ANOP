import { fakeAsync, ComponentFixture, TestBed } from '@angular/core/testing';
import { DropMenuComponent } from './drop-menu.component';

describe('DropMenuComponent', () => {
  let component: DropMenuComponent;
  let fixture: ComponentFixture<DropMenuComponent>;

  beforeEach(fakeAsync(() => {
    TestBed.configureTestingModule({
      declarations: [ DropMenuComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DropMenuComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  }));

  it('should compile', () => {
    expect(component).toBeTruthy();
  });
});
