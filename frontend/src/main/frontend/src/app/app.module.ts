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
import {ConfigToolComponent, TreeNode} from './config-tool/config-tool.component';
import {AddObjectTypeDialog} from "./config-tool/add-object-type-dialog/add-object-type-dialog";
import {UpdateObjectTypeDialog} from "./config-tool/update-object-type-dialog/update-object-type-dialog";
import {AttributesService} from "./shared/attributes/attributes.service";
import {AttrGroupsService} from "./shared/attr-groups/attr-groups.service";
import {AttrObjectTypeService} from "./shared/attr-object-type/attr-object-type.service";
import {AddAttrGroupDialog} from "./config-tool/add-attr-group-dialog/add-attr-group-dialog";


@NgModule({
  entryComponents: [
    AddObjectTypeDialog,
    UpdateObjectTypeDialog,
    AddAttrGroupDialog
  ],
  declarations: [
    AppComponent,
    ConfigToolComponent,
    AddObjectTypeDialog,
    UpdateObjectTypeDialog,
    AddAttrGroupDialog,
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
  providers: [
    ObjectTypesService,
    AttributesService,
    AttrGroupsService,
    AttrObjectTypeService
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
