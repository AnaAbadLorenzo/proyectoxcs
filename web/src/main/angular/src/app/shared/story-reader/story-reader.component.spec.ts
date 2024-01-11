import { ComponentFixture, TestBed } from '@angular/core/testing';

import { StoryReaderComponent } from './story-reader.component';

describe('StoryReaderComponent', () => {
  let component: StoryReaderComponent;
  let fixture: ComponentFixture<StoryReaderComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [StoryReaderComponent]
    });
    fixture = TestBed.createComponent(StoryReaderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
