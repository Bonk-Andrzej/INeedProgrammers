import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {JobOfferIndexComponent} from './job-offer-index.component';

describe('JobOfferIndexComponent', () => {
  let component: JobOfferIndexComponent;
  let fixture: ComponentFixture<JobOfferIndexComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [JobOfferIndexComponent]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(JobOfferIndexComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
