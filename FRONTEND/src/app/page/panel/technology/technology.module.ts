import {NgModule} from '@angular/core';

import {TechnologyIndexComponent} from "./technology-index/technology-index.component";
import {TechnologyEditComponent} from "./technology-edit/technology-edit.component";
import {TechnologyNewComponent} from "./technology-new/technology-new.component";
import {TechnologyComponent} from "./technology.component";
import {RouterModule} from "@angular/router";
import {routes} from "./routes";
import {SharedModule} from "../../../shared/shared.module";

@NgModule({
  declarations: [
    TechnologyComponent,
    TechnologyIndexComponent,
    TechnologyEditComponent,
    TechnologyNewComponent,
  ],
  imports: [RouterModule.forChild(routes), SharedModule]

})
export class TechnologyModule {
}
