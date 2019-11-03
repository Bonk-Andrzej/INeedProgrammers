import {NgModule} from '@angular/core';

import {UserComponent} from "./user.component";
import {UserEditComponent} from './user-edit/user-edit.component';
import {UserIndexComponent} from './user-index/user-index.component';
import {UserNewComponent} from './user-new/user-new.component';
import {RouterModule} from "@angular/router";
import {routes} from "./routes";

@NgModule({
  declarations: [
    UserComponent,
    UserEditComponent,
    UserIndexComponent,
    UserNewComponent
  ],
  imports: [RouterModule.forChild(routes)]

})
export class UserModule {
}
