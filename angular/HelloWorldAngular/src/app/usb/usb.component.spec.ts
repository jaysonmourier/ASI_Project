import { ComponentFixture, TestBed } from '@angular/core/testing';

import { USBComponent } from './usb.component';

describe('USBComponent', () => {
  let component: USBComponent;
  let fixture: ComponentFixture<USBComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ USBComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(USBComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
