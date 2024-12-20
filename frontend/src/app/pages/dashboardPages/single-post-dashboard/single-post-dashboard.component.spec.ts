import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SinglePostDashboardComponent } from './single-post-dashboard.component';

describe('SinglePostDashboardComponent', () => {
  let component: SinglePostDashboardComponent;
  let fixture: ComponentFixture<SinglePostDashboardComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SinglePostDashboardComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SinglePostDashboardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
