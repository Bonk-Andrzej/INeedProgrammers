import {NgModule} from '@angular/core';
import {RouterModule} from '@angular/router';

import {PasswordResetComponent} from './password-reset/password-reset.component';

import {AuthComponent} from "./auth.component";
import {SignInComponent} from "./sign-in/sign-in.component";
import {routes} from "./routes";
import {AuthTestComponent} from './auth-test/auth-test.component';


@NgModule({
  declarations: [
    AuthComponent,
    SignInComponent,
    PasswordResetComponent,
    AuthTestComponent
  ],
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AuthModule {
}
