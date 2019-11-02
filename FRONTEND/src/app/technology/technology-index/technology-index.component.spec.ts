import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {TechnologyIndexComponent} from './technology-index.component';

describe('TechnologyIndexComponent', () => {
  let component: TechnologyIndexComponent;
  let fixture: ComponentFixture<TechnologyIndexComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [TechnologyIndexComponent]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TechnologyIndexComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
