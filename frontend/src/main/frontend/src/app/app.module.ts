import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {ObjectTypesService} from './shared/object-types/object-types.service';
import {HttpClientModule} from '@angular/common/http';
import {CommonModule, NgClass} from '@angular/common';
import {
  MatButtonModule, MatCardModule, MatDialogModule, MatInputModule, MatListModule,
  MatToolbarModule
} from '@angular/material';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';


import {AppComponent} from './app.component';
import {ObjectTypesListComponent, TreeNode} from './object-types-list/object-types-list.component';
import {AddObjectTypeDialog} from "./object-types-list/add-object-type-dialog/add-object-type-dialog";


@NgModule({
  entryComponents: [AddObjectTypeDialog],
  declarations: [
    AppComponent,
    ObjectTypesListComponent,
    AddObjectTypeDialog,
    TreeNode
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    BrowserAnimationsModule,
    MatButtonModule,
    MatCardModule,
    MatInputModule,
    MatListModule,
    MatToolbarModule,
    FormsModule,
    ReactiveFormsModule,
    MatDialogModule,
    CommonModule
  ],
  providers: [ObjectTypesService],
  bootstrap: [AppComponent]
})
export class AppModule {
}
