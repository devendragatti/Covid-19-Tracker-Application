import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UserfootnavComponent } from './userfootnav.component';

describe('UserfootnavComponent', () => {
  let component: UserfootnavComponent;
  let fixture: ComponentFixture<UserfootnavComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UserfootnavComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(UserfootnavComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
