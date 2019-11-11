import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {MatButtonModule} from "@angular/material/button";
import {MatButtonToggleModule} from "@angular/material/button-toggle";
import {MaterialModule} from "./material";
import {ReactiveFormsModule} from "@angular/forms";
import {TranslateModule} from "@ngx-translate/core";

@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    MatButtonModule,
    MatButtonToggleModule,
    MaterialModule,
    ReactiveFormsModule,
    TranslateModule
  ],
  exports: [
    CommonModule,
    MatButtonModule,
    MatButtonToggleModule,
    MaterialModule,
    ReactiveFormsModule,
    TranslateModule
  ]
})
export class SharedModule {
}
