import {Routes} from '@angular/router';

import {AuthComponent} from './auth.component';
import {SignInComponent} from "./sign-in/sign-in.component";
import {PasswordResetComponent} from "./password-reset/password-reset.component";

export const routes: Routes = [
  {
    path: '',
    component: AuthComponent,
    children: [
      {
        path: 'login',
        component: SignInComponent,
        data: {
          title: 'signin'
        }
      },
      {
        path: 'password-reset',
        component: PasswordResetComponent,
        data: {
          title: 'password.reset'
        }
      }
    ]
  }
];
