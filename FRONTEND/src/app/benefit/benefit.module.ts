import {NgModule} from '@angular/core';
import {BenefitComponent} from "./benefit.component";
import {BenefitEditComponent} from "./benefit-edit/benefit-edit.component";
import {BenefitIndexComponent} from "./benefit-index/benefit-index.component";
import {BenefitNewComponent} from "./benefit-new/benefit-new.component";
import {RouterModule} from "@angular/router";
import {routes} from "./routes";

@NgModule({
  declarations: [
    BenefitComponent,
    BenefitEditComponent,
    BenefitIndexComponent,
    BenefitNewComponent,
  ],
  imports: [RouterModule.forChild(routes)]
})
export class BenefitModule {
}
