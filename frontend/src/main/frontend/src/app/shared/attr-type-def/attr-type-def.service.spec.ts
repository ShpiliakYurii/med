import { TestBed, inject } from '@angular/core/testing';

import { AttrTypeDefService } from './attr-type-def.service';

describe('AttrTypeDefService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [AttrTypeDefService]
    });
  });

  it('should be created', inject([AttrTypeDefService], (service: AttrTypeDefService) => {
    expect(service).toBeTruthy();
  }));
});
