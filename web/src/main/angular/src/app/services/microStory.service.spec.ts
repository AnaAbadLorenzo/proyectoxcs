import { TestBed } from '@angular/core/testing';

import { MicroStoryService } from './microStory.service';

describe('MicroStoryService', () => {
  let service: MicroStoryService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(MicroStoryService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
