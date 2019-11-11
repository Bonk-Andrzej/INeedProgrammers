import {Routes} from '@angular/router';

export const routes: Routes = [

  {
    path: '',
    // canActivate: [OnlyLoggedInGuard],
    loadChildren: () => import(`./page/panel/panel.module`).then(m => m.PanelModule)

  },
  {
    path: 'auth',
    // canActivate: [OnlyGuestGuard],
    loadChildren: () => import(`./page/auth/auth.module`).then(m => m.AuthModule)
  },
  {
    path: '**',
    redirectTo: '/panel'
  },
];

