import {NgModule} from '@angular/core';
import {JobOfferComponent} from "./job-offer.component";
import {JobOfferNewComponent} from "./job-offer-new/job-offer-new.component";
import {JobOfferIndexComponent} from "./job-offer-index/job-offer-index.component";
import {JobOfferEditComponent} from "./job-offer-edit/job-offer-edit.component";
import {RouterModule} from "@angular/router";
import {routes} from "./routes";

@NgModule({
  declarations: [
    JobOfferComponent,
    JobOfferNewComponent,
    JobOfferIndexComponent,
    JobOfferEditComponent
  ],
  imports: [RouterModule.forChild(routes)]
})
export class JobOfferModule {
}
