import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SalesRepresentativesComponent } from './sales-representatives.component';

describe('SalesRepresentativesComponent', () => {
  let component: SalesRepresentativesComponent;
  let fixture: ComponentFixture<SalesRepresentativesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SalesRepresentativesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SalesRepresentativesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
