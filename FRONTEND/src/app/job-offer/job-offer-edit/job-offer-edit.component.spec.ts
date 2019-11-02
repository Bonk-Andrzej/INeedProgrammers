import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {JobOfferEditComponent} from './job-offer-edit.component';

describe('JobOfferEditComponent', () => {
  let component: JobOfferEditComponent;
  let fixture: ComponentFixture<JobOfferEditComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [JobOfferEditComponent]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(JobOfferEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
