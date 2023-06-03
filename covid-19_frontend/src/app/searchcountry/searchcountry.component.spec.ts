import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SearchcountryComponent } from './searchcountry.component';

describe('SearchcountryComponent', () => {
  let component: SearchcountryComponent;
  let fixture: ComponentFixture<SearchcountryComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SearchcountryComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SearchcountryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
