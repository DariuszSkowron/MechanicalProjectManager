import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CommercialPartComponent } from './commercial-part.component';

describe('CommercialPartComponent', () => {
  let component: CommercialPartComponent;
  let fixture: ComponentFixture<CommercialPartComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CommercialPartComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CommercialPartComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
