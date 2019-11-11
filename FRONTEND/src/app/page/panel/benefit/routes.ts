import {Routes} from '@angular/router';
import {BenefitComponent} from "./benefit.component";
import {BenefitEditComponent} from "./benefit-edit/benefit-edit.component";
import {BenefitIndexComponent} from "./benefit-index/benefit-index.component";
import {BenefitNewComponent} from "./benefit-new/benefit-new.component";

export const routes: Routes = [
  {
    path: '',
    component: BenefitComponent,
    children: [
      {
        path: 'edit',
        component: BenefitEditComponent,
        data: {
          title: 'edit.benefit'
        }
      },
      {
        path: 'index',
        component: BenefitIndexComponent,
        data: {
          title: 'index.benefit'
        }
      },
      {
        path: 'new',
        component: BenefitNewComponent,
        data: {
          title: 'new.benefit'
        }
      }
    ]
  }
];
