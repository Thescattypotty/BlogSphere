import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TagsDashboardComponent } from './tags-dashboard.component';

describe('TagsDashboardComponent', () => {
  let component: TagsDashboardComponent;
  let fixture: ComponentFixture<TagsDashboardComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [TagsDashboardComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TagsDashboardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
