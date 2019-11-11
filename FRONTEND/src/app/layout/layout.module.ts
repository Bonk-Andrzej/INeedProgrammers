import {NgModule} from '@angular/core';
import {RouterModule} from '@angular/router';


import {LayoutComponent} from './layout.component';
import {LayoutHeaderComponent} from './layout-header.component';
import {LayoutMainComponent} from './layout-main.component';
import {LayoutFooterComponent} from './layout-footer.component';
import {SharedModule} from "../shared/shared.module";

const COMPONENTS = [
  LayoutComponent,
  LayoutHeaderComponent,
  LayoutMainComponent,
  LayoutFooterComponent
];

@NgModule({
  declarations: [...COMPONENTS],
  imports: [SharedModule, RouterModule],
  exports: [...COMPONENTS]
})
export class LayoutModule {
}
