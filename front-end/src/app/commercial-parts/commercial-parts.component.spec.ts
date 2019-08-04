import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CommercialPartsComponent } from './commercial-parts.component';

describe('CommercialPartsComponent', () => {
  let component: CommercialPartsComponent;
  let fixture: ComponentFixture<CommercialPartsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CommercialPartsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CommercialPartsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
