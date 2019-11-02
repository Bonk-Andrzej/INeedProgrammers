import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {SeniorityIndexComponent} from './seniority-index.component';

describe('SeniorityIndexComponent', () => {
  let component: SeniorityIndexComponent;
  let fixture: ComponentFixture<SeniorityIndexComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [SeniorityIndexComponent]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SeniorityIndexComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
