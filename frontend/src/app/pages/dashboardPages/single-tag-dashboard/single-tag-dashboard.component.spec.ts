import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SingleTagDashboardComponent } from './single-tag-dashboard.component';

describe('SingleTagDashboardComponent', () => {
  let component: SingleTagDashboardComponent;
  let fixture: ComponentFixture<SingleTagDashboardComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SingleTagDashboardComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SingleTagDashboardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
