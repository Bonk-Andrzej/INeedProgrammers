import {NgModule} from '@angular/core';
import {CategoryIndexComponent} from "./category-index/category-index.component";
import {CategoryEditComponent} from "./category-edit/category-edit.component";
import {CategoryNewComponent} from "./category-new/category-new.component";
import {CategoryComponent} from "./category.component";
import {RouterModule} from "@angular/router";
import {routes} from "./routes";

@NgModule({
  declarations: [
    CategoryComponent,
    CategoryIndexComponent,
    CategoryEditComponent,
    CategoryNewComponent,
  ],
  imports: [RouterModule.forChild(routes)]

})
export class CategoryModule {
}
