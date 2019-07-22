import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ProcessedPartsComponent } from './processed-parts.component';

describe('ProcessedPartsComponent', () => {
  let component: ProcessedPartsComponent;
  let fixture: ComponentFixture<ProcessedPartsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ProcessedPartsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ProcessedPartsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
