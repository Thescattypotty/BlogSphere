import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PostDesctiptionEditorComponent } from './post-desctiption-editor.component';

describe('PostDesctiptionEditorComponent', () => {
  let component: PostDesctiptionEditorComponent;
  let fixture: ComponentFixture<PostDesctiptionEditorComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PostDesctiptionEditorComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PostDesctiptionEditorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
