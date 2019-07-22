import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ProcessedPartComponent } from './processed-part.component';

describe('ProcessedPartComponent', () => {
  let component: ProcessedPartComponent;
  let fixture: ComponentFixture<ProcessedPartComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ProcessedPartComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ProcessedPartComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
