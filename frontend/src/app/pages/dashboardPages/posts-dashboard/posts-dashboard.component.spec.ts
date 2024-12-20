import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PostsDashboardComponent } from './posts-dashboard.component';

describe('PostsDashboardComponent', () => {
  let component: PostsDashboardComponent;
  let fixture: ComponentFixture<PostsDashboardComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PostsDashboardComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PostsDashboardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
