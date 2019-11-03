import {Routes} from '@angular/router';
import {CategoryComponent} from "./category.component";
import {CategoryNewComponent} from "./category-new/category-new.component";
import {CategoryIndexComponent} from "./category-index/category-index.component";
import {CategoryEditComponent} from "./category-edit/category-edit.component";

export const routes: Routes = [
  {
    path: '',
    component: CategoryComponent,
    children: [
      {
        path: 'edit',
        component: CategoryEditComponent,
        data: {
          title: 'edit.category'
        }
      },
      {
        path: 'index',
        component: CategoryIndexComponent,
        data: {
          title: 'index.category'
        }
      },
      {
        path: 'new',
        component: CategoryNewComponent,
        data: {
          title: 'new.category'
        }
      }
    ]
  }
];
