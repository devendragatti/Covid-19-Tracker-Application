import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UserheadnavComponent } from './userheadnav.component';

describe('UserheadnavComponent', () => {
  let component: UserheadnavComponent;
  let fixture: ComponentFixture<UserheadnavComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UserheadnavComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(UserheadnavComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
