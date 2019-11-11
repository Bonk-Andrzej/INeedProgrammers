import {Component} from '@angular/core';

@Component({
  selector: 'layout-footer',
  template: '<ng-content></ng-content>',
  host: {
    id: 'layout-footer'
  }
})
export class LayoutFooterComponent {
  /**
   * Create a new instance.
   */
  constructor() {
    //
  }
}
