import {Component, Input, OnInit} from '@angular/core';
import {ObjectTypesService} from "../shared/object-types/object-types.service";
import {AddObjectTypeDialog} from "./add-object-type-dialog/add-object-type-dialog";
import {MatDialog} from "@angular/material";
import {UpdateObjectTypeDialog} from "./update-object-type-dialog/update-object-type-dialog";

@Component({
  selector: 'app-object-types-list',
  templateUrl: './object-types-list.component.html',
  styleUrls: ['./object-types-list.component.css']
})
export class ObjectTypesListComponent implements OnInit {

  rootObjectType: Array<any>;
  selectedObjectType: any;

  constructor(private objectTypesService: ObjectTypesService, public dialog: MatDialog) {
  }

  ngOnInit() {
    this.objectTypesService.getAll().subscribe(data => {
      this.rootObjectType = data;
      this.selectedObjectType = this.rootObjectType;
    })
  }

  get self(): ObjectTypesListComponent {
    return this;
  }

  openNewDialog(): void {
    console.log(this.selectedObjectType);
    let objectType: any = {name: '', parentId: undefined, objectTypeId: undefined};
    let dialogRef = this.dialog.open(AddObjectTypeDialog, {
      width: '250px',
      data: {objectType: objectType}
    });

    dialogRef.afterClosed().subscribe(data => {
      objectType = data;
      if (objectType && objectType.name) {
        objectType.parentId = this.selectedObjectType.objectTypeId;
        console.log('The dialog was closed');
        this.objectTypesService.add(objectType).subscribe(data => {
          console.log(data);
          this.selectedObjectType.childes.push(data);
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
  styleUrls: ['./object-types-list.component.css']
})
export class TreeNode implements OnInit {
  @Input() objectType;
  @Input() selectedObjectType;
  @Input() level;

  show: boolean = false;

  private _parent: ObjectTypesListComponent;
  @Input() set parent(value: ObjectTypesListComponent) {
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
