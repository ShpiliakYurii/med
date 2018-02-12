import {Component, Input, OnInit} from '@angular/core';
import {ObjectTypesService} from "../shared/object-types/object-types.service";
import {AddObjectTypeDialog} from "./add-object-type-dialog/add-object-type-dialog";
import {MatDialog} from "@angular/material";

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

  openDialog(): void {
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

}

@Component({
  selector: 'tree-node',
  template: `
    <div *ngIf="objectType" (click)="changeSelectedObjectType(objectType)"
         [ngClass]="{'selected-item': objectType.objectTypeId==_parent.selectedObjectType.objectTypeId}"
         [ngStyle]="{'padding-left': getLeftPadding()}" (dblclick)="show = !show">
      <span [hidden]="objectType.childes.length == 0">
        <span class="pointer" (click)="show = !show" [hidden]="show">+</span>
        <span class="pointer" (click)="show = !show" [hidden]="!show">-</span>
      </span>{{objectType.name}}
    </div>
    <div *ngIf="objectType" [hidden]="!show">
      <div *ngFor="let ot of objectType.childes">
        <tree-node [objectType]="ot" [parent]="_parent" [level]="level + 1"></tree-node>
      </div>
    </div>
  `,
  styleUrls: ['./object-types-list.component.css']
})
export class TreeNode {
  @Input() objectType;
  @Input() selectedObjectType;
  @Input() level;

  show: boolean = false;

  private _parent: ObjectTypesListComponent;
  @Input() set parent(value: ObjectTypesListComponent) {
    this._parent = value;
  }

  get parent(): ObjectTypesListComponent {
    return this._parent;
  }

  getLeftPadding(): string {
    return this.level * 10 + "px";
  }


  changeSelectedObjectType(ot: any): void {
    this._parent.selectedObjectType = ot;
  }

}

// (click)="changeSelectedObjectType(objectType)"
// <!--<mat-list-item *ngFor="let ot of objectType.childes"-->
// <!--[ngClass]="{'selected-item': ot.objectTypeId==selectedObjectType.objectTypeId}">-->
// <!--<tree-node [objectType] = "ot" > </tree-node>-->
// < !--</mat-list-item>-->
// </mat-list>
