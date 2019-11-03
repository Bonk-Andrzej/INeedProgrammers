import {Routes} from '@angular/router';
import {LocationComponent} from "./location.component";
import {LocationNewComponent} from "./location-new/location-new.component";
import {LocationIndexComponent} from "./location-index/location-index.component";
import {LocationEditComponent} from "./location-edit/location-edit.component";

export const routes: Routes = [
  {
    path: '',
    component: LocationComponent,
    children: [
      {
        path: 'edit',
        component: LocationEditComponent,
        data: {
          title: 'edit.location'
        }
      },
      {
        path: 'index',
        component: LocationIndexComponent,
        data: {
          title: 'index.location'
        }
      },
      {
        path: 'new',
        component: LocationNewComponent,
        data: {
          title: 'new.location'
        }
      }
    ]
  }
];
