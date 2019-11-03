import {NgModule} from '@angular/core';
import {RouterModule} from '@angular/router';

import {PasswordResetComponent} from './password-reset/password-reset.component';

import {AuthComponent} from "./auth.component";
import {SignInComponent} from "./sign-in/sign-in.component";
import {routes} from "./routes";


@NgModule({
  declarations: [
    AuthComponent,
    SignInComponent,
    PasswordResetComponent,
  ],
  imports: [RouterModule.forChild(routes)]
})
export class AuthModule {
}
