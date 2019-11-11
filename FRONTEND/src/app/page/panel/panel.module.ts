import {NgModule} from '@angular/core';
import {PanelComponent} from "./panel.component";
import {PanelDashboardComponent} from "./panel-dashboard.component";
import {RouterModule} from "@angular/router";
import {SharedModule} from "../../shared/shared.module";
import {LayoutModule} from "../../layout/layout.module";
import {routes} from "./routes";
import {NavBarComponent} from './nav-bar/nav-bar.component';
import {FooterComponent} from './footer/footer.component';

@NgModule({
  declarations: [PanelComponent, PanelDashboardComponent, NavBarComponent, FooterComponent],
  imports: [
    RouterModule.forChild(routes),
    SharedModule,
    LayoutModule,

  ]
})
export class PanelModule {
}
