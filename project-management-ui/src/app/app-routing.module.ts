import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  {
    path: 'ch-prj',
    loadChildren: () =>
      import('./modules/chef-projet/chef-projet.module').then(
        (m) => m.ChefProjetModule
      ),
  },
  {
    path: 'ges-ao-c',
    loadChildren: () =>
      import('./modules/ges-ao-c/ges-ao-c.module').then((m) => m.GesAoCModule),
  },
  {
    path: 'f',
    loadChildren: () =>
      import('./modules/financier/financier.module').then(
        (m) => m.FinancierModule
      ),
  },
  {
    path: 'admin',
    loadChildren: () =>
      import('./modules/admin/admin.module').then((m) => m.AdminModule),
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
