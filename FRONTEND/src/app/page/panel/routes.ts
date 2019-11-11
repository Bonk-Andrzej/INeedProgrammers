import {Routes} from '@angular/router';

import {PanelComponent} from './panel.component';

export const routes: Routes = [
  {
    path: '',
    component: PanelComponent,
    children: [
      // {
      //   path: '',
      //   component: PanelDashboardComponent
      // },
      {
        path: '',
        loadChildren: () => import(`./job-offer/job-offer.module`).then(m => m.JobOfferModule)
      },
      {
        path: 'users',
        loadChildren: () => import(`./user/user.module`).then(m => m.UserModule)
      },
      {
        path: 'benefits',
        loadChildren: () => import(`./benefit/benefit.module`).then(m => m.BenefitModule)
      },
      {
        path: 'categories',
        loadChildren: () => import(`./category/category.module`).then(m => m.CategoryModule)
      },

      {
        path: 'locations',
        loadChildren: () => import(`./location/location.module`).then(m => m.LocationModule)
      },
      {
        path: 'seniorities',
        loadChildren: () => import(`./seniority/seniority.module`).then(m => m.SeniorityModule)
      },
      {
        path: 'technologies',
        loadChildren: () => import(`./technology/technology.module`).then(m => m.TechnologyModule)
      }
    ]
  },
];
// todo add another module for one job-offer?
// {
//         path: 'job-offers/:id',
//         loadChildren: () => import(`./job-offer/job-offer.module`).then(m => m.JobOfferModule)
//       },
