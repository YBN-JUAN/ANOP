import { fakeAsync, ComponentFixture, TestBed } from '@angular/core/testing';
import { PublishCenterGroupListComponent } from './publish-center-group-list.component';

describe('PublishCenterGroupListComponent', () => {
  let component: PublishCenterGroupListComponent;
  let fixture: ComponentFixture<PublishCenterGroupListComponent>;

  beforeEach(fakeAsync(() => {
    TestBed.configureTestingModule({
      declarations: [ PublishCenterGroupListComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PublishCenterGroupListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  }));

  it('should compile', () => {
    expect(component).toBeTruthy();
  });
});
