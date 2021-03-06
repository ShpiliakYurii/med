import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {ObjectTypesService} from './shared/object-types/object-types.service';
import {HttpClientModule} from '@angular/common/http';
import {CommonModule, NgClass} from '@angular/common';
import {
  MatButtonModule, MatCardModule, MatCheckboxModule, MatDialogModule, MatInputModule, MatListModule, MatSelectModule,
  MatTableModule,
  MatToolbarModule
} from '@angular/material';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';


import {AppComponent} from './app.component';
import {ConfigToolComponent, TreeNode} from './config-tool/config-tool.component';
import {AddObjectTypeDialog} from "./config-tool/add-object-type-dialog/add-object-type-dialog";
import {UpdateObjectTypeDialog} from "./config-tool/update-object-type-dialog/update-object-type-dialog";
import {AttributesService} from "./shared/attributes/attributes.service";
import {AttrGroupsService} from "./shared/attr-groups/attr-groups.service";
import {AttrObjectTypeService} from "./shared/attr-object-type/attr-object-type.service";
import {AddAttrGroupDialog} from "./config-tool/add-attr-group-dialog/add-attr-group-dialog";
import {AttrTypeDefSearchDialog} from "./config-tool/attr-type-def-search-dialog/attr-type-def-search-dialog";
import {AttrTypeDefService} from "./shared/attr-type-def/attr-type-def.service";
import {AddAttrTypeDefDialog} from "./config-tool/add-attr-type-def-dialog/add-attr-type-def-dialog";
import {AttrGroupSearchDialog} from "./config-tool/attr-group-search-dialog/attr-group-search-dialog";
import {ListValueService} from "./shared/list-value/list-value.service";


@NgModule({
  entryComponents: [
    AddObjectTypeDialog,
    UpdateObjectTypeDialog,
    AddAttrGroupDialog,
    AttrTypeDefSearchDialog,
    AddAttrTypeDefDialog,
    AttrGroupSearchDialog
  ],
  declarations: [
    AppComponent,
    ConfigToolComponent,
    AddObjectTypeDialog,
    UpdateObjectTypeDialog,
    AddAttrGroupDialog,
    AttrTypeDefSearchDialog,
    AddAttrTypeDefDialog,
    AttrGroupSearchDialog,
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
    MatSelectModule,
    MatCheckboxModule,
    FormsModule,
    ReactiveFormsModule,
    MatDialogModule,
    MatTableModule,
    CommonModule,
    NgbModule
  ],
  providers: [
    ObjectTypesService,
    AttributesService,
    AttrGroupsService,
    AttrObjectTypeService,
    AttrTypeDefService,
    ListValueService
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
