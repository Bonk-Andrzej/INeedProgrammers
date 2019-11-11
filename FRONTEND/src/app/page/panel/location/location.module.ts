import {NgModule} from '@angular/core';
import {LocationIndexComponent} from "./location-index/location-index.component";
import {LocationEditComponent} from "./location-edit/location-edit.component";
import {LocationNewComponent} from "./location-new/location-new.component";
import {LocationComponent} from "./location.component";
import {RouterModule} from "@angular/router";
import {routes} from "./routes";

@NgModule({
  declarations: [
    LocationComponent,
    LocationIndexComponent,
    LocationEditComponent,
    LocationNewComponent,
  ],
  imports: [RouterModule.forChild(routes)]

})
export class LocationModule {
}
