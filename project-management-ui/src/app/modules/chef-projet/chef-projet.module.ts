import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ChefProjetRoutingModule } from './chef-projet-routing.module';
import { MainComponent } from './pages/main/main.component';
import { ListePageComponent } from './pages/liste-page/liste-page.component';


@NgModule({
  declarations: [
    MainComponent,
    ListePageComponent
  ],
  imports: [
    CommonModule,
    ChefProjetRoutingModule
  ]
})
export class ChefProjetModule { }
