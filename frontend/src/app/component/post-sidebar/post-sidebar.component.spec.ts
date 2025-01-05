import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PostSidebarComponent } from './post-sidebar.component';

describe('PostSidebarComponent', () => {
  let component: PostSidebarComponent;
  let fixture: ComponentFixture<PostSidebarComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PostSidebarComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PostSidebarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
