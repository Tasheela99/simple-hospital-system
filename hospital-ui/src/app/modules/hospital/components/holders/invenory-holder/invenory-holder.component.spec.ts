import { ComponentFixture, TestBed } from '@angular/core/testing';

import { InvenoryHolderComponent } from './invenory-holder.component';

describe('InvenoryHolderComponent', () => {
  let component: InvenoryHolderComponent;
  let fixture: ComponentFixture<InvenoryHolderComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [InvenoryHolderComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(InvenoryHolderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
