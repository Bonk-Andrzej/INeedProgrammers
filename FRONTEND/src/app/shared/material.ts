import {NgModule} from '@angular/core';

import {
  MatButtonModule,
  MatButtonToggleModule,
  MatDividerModule,
  MatExpansionModule,
  MatListModule,
  MatPaginatorIntl,
  MatSidenavModule,
  MatSortModule
} from '@angular/material';
import {MatIconModule} from '@angular/material/icon';
import {MatToolbarModule} from '@angular/material/toolbar';
import {MatMenuModule} from '@angular/material/menu';
import {MatCardModule} from '@angular/material/card';
import {MatInputModule} from '@angular/material/input';
import {MatSelectModule} from '@angular/material/select';
import {MatProgressBarModule} from '@angular/material/progress-bar';
import {MAT_SNACK_BAR_DEFAULT_OPTIONS, MatSnackBarModule} from '@angular/material/snack-bar';
import {MatTableModule} from '@angular/material/table';
import {MatPaginatorModule} from '@angular/material/paginator';
import {MatDialogModule} from '@angular/material/dialog';
import {MatTooltipModule} from '@angular/material/tooltip';
import {MatCheckboxModule} from '@angular/material/checkbox';
import {MatSlideToggleModule} from '@angular/material/slide-toggle';
import {MatBottomSheetModule} from '@angular/material/bottom-sheet';
import {MatProgressSpinnerModule} from '@angular/material/progress-spinner';
import {DragDropModule} from '@angular/cdk/drag-drop';
import {MatTabsModule} from '@angular/material/tabs';
import {MatAutocompleteModule} from '@angular/material/autocomplete';

export class MatPaginatorIntlWithoutLabels extends MatPaginatorIntl {
  /**
   * @var {string}
   */
  nextPageLabel: string = null;

  /**
   * @var {string}
   */
  previousPageLabel: string = null;

  /**
   * Generate the range label.
   *
   * @param {number} page
   * @param {number} pageSize
   * @param {number} length
   * @return {string}
   */
  getRangeLabel = (page: number, pageSize: number, length: number): string => {
    const numberOfPages = Math.ceil(length / pageSize);

    return `${page + 1} / ${numberOfPages}`;
  };
}

const MODULES = [
  MatIconModule,
  MatButtonModule,
  MatToolbarModule,
  MatMenuModule,
  MatCardModule,
  MatInputModule,
  MatSelectModule,
  MatProgressBarModule,
  MatSnackBarModule,
  MatTableModule,
  MatPaginatorModule,
  MatDialogModule,
  MatTooltipModule,
  MatCheckboxModule,
  MatSlideToggleModule,
  MatBottomSheetModule,
  MatProgressSpinnerModule,
  DragDropModule,
  MatButtonToggleModule,
  MatDividerModule,
  MatExpansionModule,
  MatListModule,
  MatSidenavModule,
  MatTabsModule,
  MatSortModule,
  MatAutocompleteModule
];

@NgModule({
  imports: MODULES,
  exports: MODULES,
  providers: [
    {provide: MatPaginatorIntl, useClass: MatPaginatorIntlWithoutLabels},
    {
      provide: MAT_SNACK_BAR_DEFAULT_OPTIONS,
      useValue: {duration: 2500, horizontalPosition: 'right'}
    }
  ]
})
export class MaterialModule {
}
