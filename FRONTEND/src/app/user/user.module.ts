import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {UserRoutingModule} from './user-routing.module';
import {UserComponent} from "./user.component";
import {UserEditComponent} from './user-edit/user-edit.component';
import {UserIndexComponent} from './user-index/user-index.component';
import {UserNewComponent} from './user-new/user-new.component';

@NgModule({
  declarations: [UserComponent, UserEditComponent, UserIndexComponent, UserNewComponent],
  imports: [
    CommonModule,
    UserRoutingModule
  ]
})
export class UserModule {
}
