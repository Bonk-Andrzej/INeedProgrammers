import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {BenefitNewComponent} from './benefit-new.component';

describe('BenefitNewComponent', () => {
  let component: BenefitNewComponent;
  let fixture: ComponentFixture<BenefitNewComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [BenefitNewComponent]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BenefitNewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
