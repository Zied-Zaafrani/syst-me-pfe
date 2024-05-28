import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListePageComponent } from './liste-page.component';

describe('ListePageComponent', () => {
  let component: ListePageComponent;
  let fixture: ComponentFixture<ListePageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ListePageComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ListePageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
