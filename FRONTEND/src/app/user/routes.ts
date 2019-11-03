import {Routes} from '@angular/router';
import {UserComponent} from "./user.component";
import {UserNewComponent} from "./user-new/user-new.component";
import {UserIndexComponent} from "./user-index/user-index.component";
import {UserEditComponent} from "./user-edit/user-edit.component";

export const routes: Routes = [
  {
    path: '',
    component: UserComponent,
    children: [
      {
        path: 'edit',
        component: UserEditComponent,
        data: {
          title: 'edit.user'
        }
      },
      {
        path: 'index',
        component: UserIndexComponent,
        data: {
          title: 'index.user'
        }
      },
      {
        path: 'new',
        component: UserNewComponent,
        data: {
          title: 'new.user'
        }
      }
    ]
  }
];
