import { ComponentFixture, TestBed } from '@angular/core/testing';

import { StockAccessoiresComponent } from './stock-accessoires.component';

describe('StockAccessoiresComponent', () => {
  let component: StockAccessoiresComponent;
  let fixture: ComponentFixture<StockAccessoiresComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ StockAccessoiresComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(StockAccessoiresComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
