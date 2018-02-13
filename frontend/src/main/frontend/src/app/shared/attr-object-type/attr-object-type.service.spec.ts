import { TestBed, inject } from '@angular/core/testing';

import { AttrObjectTypeService } from './attr-object-type.service';

describe('AttrObjectTypeService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [AttrObjectTypeService]
    });
  });

  it('should be created', inject([AttrObjectTypeService], (service: AttrObjectTypeService) => {
    expect(service).toBeTruthy();
  }));
});
