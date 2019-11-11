import {Routes} from '@angular/router';
import {SeniorityComponent} from "./seniority.component";
import {SeniorityIndexComponent} from "./seniority-index/seniority-index.component";
import {SeniorityNewComponent} from "./seniority-new/seniority-new.component";
import {SeniorityEditComponent} from "./seniority-edit/seniority-edit.component";

export const routes: Routes = [
  {
    path: '',
    component: SeniorityComponent,
    children: [
      {
        path: 'edit',
        component: SeniorityEditComponent,
        data: {
          title: 'edit.seniority'
        }
      },
      {
        path: 'index',
        component: SeniorityIndexComponent,
        data: {
          title: 'index.seniority'
        }
      },
      {
        path: 'new',
        component: SeniorityNewComponent,
        data: {
          title: 'new.seniority'
        }
      }
    ]
  }
];
