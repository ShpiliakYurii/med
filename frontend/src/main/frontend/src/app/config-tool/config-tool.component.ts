import {Component, Input, OnInit} from '@angular/core';
import {ObjectTypesService} from "../shared/object-types/object-types.service";
import {AddObjectTypeDialog} from "./add-object-type-dialog/add-object-type-dialog";
import {MatDialog} from "@angular/material";
import {UpdateObjectTypeDialog} from "./update-object-type-dialog/update-object-type-dialog";
import {AddAttrGroupDialog} from "./add-attr-group-dialog/add-attr-group-dialog";
import {AttrGroupsService} from "../shared/attr-groups/attr-groups.service";
import {AttrTypeDefSearchDialog} from "./attr-type-def-search-dialog/attr-type-def-search-dialog";
import {AddAttrTypeDefDialog} from "./add-attr-type-def-dialog/add-attr-type-def-dialog";
import {AttrGroupSearchDialog} from "./attr-group-search-dialog/attr-group-search-dialog";

@Component({
  selector: 'config-tool',
  templateUrl: './config-tool.html',
  styleUrls: ['./config-tool.css']
})
export class ConfigToolComponent implements OnInit {

  //VARIABLES BLOCK START
  rootObjectType: Array<any>;
  attrGroups: Array<any>;
  selectedObjectType: any;
  attrGroupForAdding: any = {name: '', attrGroupId: undefined, subgroup: ''};
  selectedAttribute: any = {name: '', attrGroupId: undefined, attrTypeId: 1, attrTypeDefId: undefined};
  attrTypeDefForAdding: any = {name: '', attrTypeDefId: undefined, attrTypeId: 4};
  selectedAttributeGroup: any = {name: '', attrGroupId: undefined, subgroup: ''};

  //VARIABLES BLOCK END

  constructor(private objectTypesService: ObjectTypesService, private attrGroupsService: AttrGroupsService,
              public dialog: MatDialog) {
  }

  ngOnInit() {
    this.objectTypesService.getAll().subscribe(data => {
      this.rootObjectType = data;
      console.log(this.rootObjectType);
      this.selectedObjectType = this.rootObjectType;
    });

    this.attrGroupsService.getAll().subscribe(data => {
      this.attrGroups = data;
    })
  }

  get self(): ConfigToolComponent {
    return this;
  }

  openNewDialog(): void {
    let objectType: any = {name: '', parentId: undefined, objectTypeId: undefined};
    let dialogRef = this.dialog.open(AddObjectTypeDialog, {
      width: '250px',
      data: {objectType: objectType}
    });

    dialogRef.afterClosed().subscribe(data => {
      objectType = data;
      if (objectType && objectType.name) {
        objectType.parentId = this.selectedObjectType.objectTypeId;
        this.objectTypesService.add(objectType).subscribe(data => {
          this.selectedObjectType.childes.push(data);
        });
      }
    });
  }

  openNewAttrGroupDialog(): void {
    let dialogRef = this.dialog.open(AddAttrGroupDialog, {
      width: '250px',
      data: {attrGroup: this.attrGroupForAdding}
    });

    dialogRef.afterClosed().subscribe(data => {
      if (this.attrGroupForAdding.name && this.attrGroupForAdding.subgroup) {
        console.log(this.attrGroupForAdding);
        this.attrGroupsService.add(this.attrGroupForAdding).subscribe(data => {
          this.attrGroups.push(data);
          this.attrGroupForAdding = {name: '', attrGroupId: undefined, subgroup: ''};
        });
      }
    });
  }

  openEditDialog(): void {
    let dialogRef = this.dialog.open(UpdateObjectTypeDialog, {
      width: '250px',
      data: {objectType: this.selectedObjectType}
    });

    dialogRef.afterClosed().subscribe(data => {
      if (this.selectedObjectType.name) {
        this.objectTypesService.update(this.selectedObjectType).subscribe(data => {
        });
      }
    });
  }

  openAttrGroupSearchDialog(): void {
    let dialogRef = this.dialog.open(AttrGroupSearchDialog, {
      width: '600px',
      data: {}
    });

    dialogRef.afterClosed().subscribe(data => {
      console.log(data);
      if (data && data.length > 0)
        this.selectedAttributeGroup = data[0];
    });
  }

  openAttrTypeDefSearchDialog(): void {
    let dialogRef = this.dialog.open(AttrTypeDefSearchDialog, {
      width: '400px',
      data: {}
    });

    dialogRef.afterClosed().subscribe(data => {
      console.log(data);
    });
  }

  openAddAttrTypeDefDialog(): void {
    let dialogRef = this.dialog.open(AddAttrTypeDefDialog, {
      width: '400px',
      data: {attrTypeDef: this.attrTypeDefForAdding}
    });

    dialogRef.afterClosed().subscribe(data => {
      console.log(data);
    });
  }

  delete(): void {
    this.objectTypesService.remove(this.selectedObjectType.objectTypeId).subscribe(data => {
      this.removeObjectTypeFromHierarchy(this.rootObjectType, this.selectedObjectType);
    });
  }

  removeObjectTypeFromHierarchy(parent: any, objectType: any): void {
    parent.childes.forEach((entry, index) => {
      if (entry.objectTypeId == objectType.objectTypeId) {
        parent.childes.splice(index, 1);
        this.selectedObjectType = parent;
        return;
      }
      this.removeObjectTypeFromHierarchy(entry, objectType);
    });
  }

}

@Component({
  selector: 'tree-node',
  templateUrl: 'tree-element.component.html',
  styleUrls: ['./config-tool.css']
})
export class TreeNode implements OnInit {
  @Input() objectType;
  @Input() selectedObjectType;
  @Input() level;

  show: boolean = false;

  private _parent: ConfigToolComponent;
  @Input() set parent(value: ConfigToolComponent) {
    this._parent = value;
  }

  getLeftPadding(): string {
    return this.level * 10 + "px";
  }


  changeSelectedObjectType(ot: any): void {
    this._parent.selectedObjectType = ot;
  }

  ngOnInit() {
    if (this.level == 0) this.show = true;
  }

}
