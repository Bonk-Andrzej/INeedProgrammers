import {Routes} from '@angular/router';

export const routes: Routes = [
  {path: '', redirectTo: 'home', pathMatch: 'full'},
  {path: 'auth', loadChildren: () => import(`./auth/auth.module`).then(m => m.AuthModule)},
  {path: 'user', loadChildren: () => import(`./user/user.module`).then(m => m.UserModule)},
  {path: 'benefit', loadChildren: () => import(`./benefit/benefit.module`).then(m => m.BenefitModule)},
  {path: 'category', loadChildren: () => import(`./category/category.module`).then(m => m.CategoryModule)},
  {path: 'job-offer', loadChildren: () => import(`./job-offer/job-offer.module`).then(m => m.JobOfferModule)},
  {path: 'location', loadChildren: () => import(`./location/location.module`).then(m => m.LocationModule)},
  {path: 'seniority', loadChildren: () => import(`./seniority/seniority.module`).then(m => m.SeniorityModule)},
  {path: 'technology', loadChildren: () => import(`./technology/technology.module`).then(m => m.TechnologyModule)},
];

