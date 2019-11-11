import {Component} from '@angular/core';

@Component({
  selector: 'layout-header',
  templateUrl: './layout-header.component.html',
  host: {
    id: 'layout-header'
  }
})
export class LayoutHeaderComponent {
  /**
   * @var {boolean}
   */
  isProcessing: boolean;

  /**
   * Create a new instance.
   */
  constructor() {
    //
  }
}
