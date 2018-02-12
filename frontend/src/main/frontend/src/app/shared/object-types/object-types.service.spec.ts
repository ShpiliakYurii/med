import { TestBed, inject } from '@angular/core/testing';

import { ObjectTypesService } from './object-types.service';

describe('ObjectTypesService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [ObjectTypesService]
    });
  });

  it('should be created', inject([ObjectTypesService], (service: ObjectTypesService) => {
    expect(service).toBeTruthy();
  }));
});
