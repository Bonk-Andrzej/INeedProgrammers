import {Routes} from '@angular/router';
import {TechnologyComponent} from "./technology.component";
import {TechnologyNewComponent} from "./technology-new/technology-new.component";
import {TechnologyIndexComponent} from "./technology-index/technology-index.component";
import {TechnologyEditComponent} from "./technology-edit/technology-edit.component";

export const routes: Routes = [
  {
    path: '',
    component: TechnologyComponent,
    children: [
      {
        path: 'edit',
        component: TechnologyEditComponent,
        data: {
          title: 'edit.technology'
        }
      },
      {
        path: 'index',
        component: TechnologyIndexComponent,
        data: {
          title: 'index.technology'
        }
      },
      {
        path: 'new',
        component: TechnologyNewComponent,
        data: {
          title: 'new.technology'
        }
      }
    ]
  }
];
