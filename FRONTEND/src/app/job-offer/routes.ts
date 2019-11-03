import {Routes} from '@angular/router';
import {JobOfferComponent} from "./job-offer.component";
import {JobOfferIndexComponent} from "./job-offer-index/job-offer-index.component";
import {JobOfferEditComponent} from "./job-offer-edit/job-offer-edit.component";
import {JobOfferNewComponent} from "./job-offer-new/job-offer-new.component";

export const routes: Routes = [
  {
    path: '',
    component: JobOfferComponent,
    children: [
      {
        path: 'edit',
        component: JobOfferEditComponent,
        data: {
          title: 'edit.job.offer'
        }
      },
      {
        path: 'index',
        component: JobOfferIndexComponent,
        data: {
          title: 'index.job.offer'
        }
      },
      {
        path: 'new',
        component: JobOfferNewComponent,
        data: {
          title: 'new.job.offer'
        }
      }
    ]
  }
];
