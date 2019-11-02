import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {SeniorityEditComponent} from './seniority-edit.component';

describe('SeniorityEditComponent', () => {
  let component: SeniorityEditComponent;
  let fixture: ComponentFixture<SeniorityEditComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [SeniorityEditComponent]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SeniorityEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
