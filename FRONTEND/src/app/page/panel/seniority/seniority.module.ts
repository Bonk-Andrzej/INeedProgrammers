import {NgModule} from '@angular/core';

import {SeniorityIndexComponent} from "./seniority-index/seniority-index.component";
import {SeniorityNewComponent} from "./seniority-new/seniority-new.component";
import {SeniorityEditComponent} from "./seniority-edit/seniority-edit.component";
import {SeniorityComponent} from "./seniority.component";
import {RouterModule} from "@angular/router";
import {routes} from "./routes";

@NgModule({
  declarations: [
    SeniorityComponent,
    SeniorityIndexComponent,
    SeniorityNewComponent,
    SeniorityEditComponent,
  ],
  imports: [RouterModule.forChild(routes)]

})
export class SeniorityModule {
}
