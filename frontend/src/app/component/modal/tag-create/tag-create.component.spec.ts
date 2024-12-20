import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TagCreateComponent } from './tag-create.component';

describe('TagCreateComponent', () => {
  let component: TagCreateComponent;
  let fixture: ComponentFixture<TagCreateComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [TagCreateComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TagCreateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
