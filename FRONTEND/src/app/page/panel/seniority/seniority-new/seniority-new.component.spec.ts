import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {SeniorityNewComponent} from './seniority-new.component';

describe('SeniorityNewComponent', () => {
  let component: SeniorityNewComponent;
  let fixture: ComponentFixture<SeniorityNewComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [SeniorityNewComponent]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SeniorityNewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
