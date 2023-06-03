import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HomeheadnavComponent } from './homeheadnav.component';

describe('HomeheadnavComponent', () => {
  let component: HomeheadnavComponent;
  let fixture: ComponentFixture<HomeheadnavComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ HomeheadnavComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(HomeheadnavComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
