import { TestBed, inject } from '@angular/core/testing';

import { ListValueService } from './list-value.service';

describe('ListValueService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [ListValueService]
    });
  });

  it('should be created', inject([ListValueService], (service: ListValueService) => {
    expect(service).toBeTruthy();
  }));
});
