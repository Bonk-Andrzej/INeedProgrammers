import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {BenefitIndexComponent} from './benefit-index.component';

describe('BenefitIndexComponent', () => {
  let component: BenefitIndexComponent;
  let fixture: ComponentFixture<BenefitIndexComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [BenefitIndexComponent]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BenefitIndexComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
