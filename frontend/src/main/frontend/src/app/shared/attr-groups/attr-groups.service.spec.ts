import { TestBed, inject } from '@angular/core/testing';

import { AttrGroupsService } from './attr-groups.service';

describe('AttrGroupsService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [AttrGroupsService]
    });
  });

  it('should be created', inject([AttrGroupsService], (service: AttrGroupsService) => {
    expect(service).toBeTruthy();
  }));
});
