import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { KweetFormComponent } from './kweet-form.component';

describe('KweetFormComponent', () => {
  let component: KweetFormComponent;
  let fixture: ComponentFixture<KweetFormComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ KweetFormComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(KweetFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
