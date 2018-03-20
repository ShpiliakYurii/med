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
import {ListValueService} from "../shared/list-value/list-value.service";
import {AttrTypeDefService} from "../shared/attr-type-def/attr-type-def.service";
import {AttributesService} from "../shared/attributes/attributes.service";
import {AttrObjectTypeService} from "../shared/attr-object-type/attr-object-type.service";

@Component({
  selector: 'config-tool',
  templateUrl: './config-tool.html',
  styleUrls: ['./config-tool.css', 'general-dialog-styles.css']
})
export class ConfigToolComponent implements OnInit {

  //VARIABLES BLOCK START
  rootObjectType: Array<any>;
  attrGroups: Array<any>;
  selectedObjectType: any;
  attrGroupForAdding: any = {name: '', attrGroupId: undefined, subgroup: ''};
  selectedAttribute: any = {name: '', attrGroupId: undefined, attrTypeId: 1, attrTypeDefId: undefined};
  attrTypeDefForAdding: any = {name: '', attrTypeDefId: undefined, attrTypeId: 4, listValues: []};
  selectedAttributeGroup: any = {name: '', attrGroupId: undefined, subgroup: ''};
  listValues: Array<any> = [];
  selectedAttrTypeDef: any = {name: ''};
  aots: any;
  aotsKeys: Array<any>;

  //VARIABLES BLOCK END

  constructor(private objectTypesService: ObjectTypesService, private attrGroupsService: AttrGroupsService,
              private attrTypeDefService: AttrTypeDefService, private listValueService: ListValueService,
              private attributesService: AttributesService, private attrObjectTypeService: AttrObjectTypeService,
              public dialog: MatDialog) {
  }

  ngOnInit() {
    this.objectTypesService.getAll().subscribe(data => {
      this.rootObjectType = data;
      this.selectedObjectType = this.rootObjectType;
      this.loadAOTS(this.selectedObjectType.objectTypeId);
    });

    this.attrGroupsService.getAll().subscribe(data => {
      this.attrGroups = data;
    })
  }

  prepareNewAttribute() {
    this.selectedAttribute.name = '';
    this.selectedAttribute.attrId = undefined;
  }

  getAttrById(attrId: number) {
    if (this.selectedAttribute.attrId != attrId) {
      this.attributesService.getById(attrId).subscribe(data => {
        this.selectedAttribute = data;
        console.log(this.selectedAttribute);
        this.updateSelectedAttributeInformation();
      });
    }
  }

  updateSelectedAttributeInformation() {
    if (this.selectedAttributeGroup.attrGroupId != this.selectedAttribute.attrGroupId) {
      this.attrGroupsService.getById(this.selectedAttribute.attrGroupId).subscribe(data => {
        this.selectedAttributeGroup = data;
      });
    }
    if ((this.selectedAttribute.attrTypeId == 4 || this.selectedAttribute.attrTypeId == 5) &&
      this.selectedAttrTypeDef.attrTypeDefId != this.selectedAttribute.attrTypeDefId) {
      this.attrTypeDefService.getById(this.selectedAttribute.attrTypeDefId).subscribe(data => {
        this.selectedAttrTypeDef = data;
      });
    }
  }

  copyToClipboard(val: string) {
    let selBox = document.createElement('textarea');
    selBox.style.position = 'fixed';
    selBox.style.left = '0';
    selBox.style.top = '0';
    selBox.style.opacity = '0';
    selBox.value = val;
    document.body.appendChild(selBox);
    selBox.focus();
    selBox.select();
    document.execCommand('copy');
    document.body.removeChild(selBox);
  }

  loadAOTS(objectTypeId) {
    this.attrObjectTypeService.getByObjectType(objectTypeId, false).subscribe(data => {
      this.aots = data;
      this.aotsKeys = Object.keys(this.aots);
      for (let prop in this.aots) {
        console.log(this.aots[prop][0]);
      }
    });
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

  openAddAttrGroupDialog(): void {
    let dialogRef = this.dialog.open(AddAttrGroupDialog, {
      width: '250px',
      data: {attrGroup: this.attrGroupForAdding}
    });

    dialogRef.afterClosed().subscribe(data => {
      if (this.attrGroupForAdding.name && this.attrGroupForAdding.subgroup) {
        console.log(this.attrGroupForAdding);
        this.attrGroupsService.add(this.attrGroupForAdding).subscribe(data => {
          this.attrGroups.push(data);
          this.selectedAttributeGroup = data;
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
      if (data && data.length > 0)
        this.selectedAttributeGroup = data[0];
    });
  }

  openAttrTypeDefSearchDialog(): void {
    let dialogRef = this.dialog.open(AttrTypeDefSearchDialog, {
      width: '600px',
      data: {}
    });

    dialogRef.afterClosed().subscribe(data => {
      if (data && data.length > 0)
        this.selectedAttrTypeDef = data[0];
    });
  }

  openAddAttrTypeDefDialog(): void {
    let dialogRef = this.dialog.open(AddAttrTypeDefDialog, {
      width: '600px',
      maxHeight: '600px',
      data: {
        attrTypeDef: this.attrTypeDefForAdding,
        listValues: this.listValues
      }
    });

    dialogRef.afterClosed().subscribe(data => {
      this.attrTypeDefService.add(this.attrTypeDefForAdding).subscribe(data => {
        this.selectedAttrTypeDef = data;
        for (let lv of this.listValues) {
          lv.attrTypeDefId = this.selectedAttrTypeDef.attrTypeDefId;
          this.listValueService.add(lv).subscribe(data => {
            console.log(data);
          })
        }
      });
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
        this.loadAOTS(this.selectedObjectType.objectTypeId);
        return;
      }
      this.removeObjectTypeFromHierarchy(entry, objectType);
    });
  }

  addAttribute(): void {
    if (this.validateAttributeParameters()) {
      this.selectedAttribute.attrGroupId = this.selectedAttributeGroup.attrGroupId;
      this.selectedAttribute.attrTypeDefId = this.selectedAttrTypeDef.attrTypeDefId;
      //TODO change 0 to options calculating
      this.attributesService.addAndBind(this.selectedAttribute, this.selectedObjectType.objectTypeId, 0).subscribe(data => {
        this.selectedAttribute = data;
        this.selectedAttribute.attrName = this.selectedAttribute.name;
        this.aots[this.selectedAttributeGroup.name].push(this.selectedAttribute);
        // this.loadAOTS(this.selectedObjectType.objectTypeId);
      });
      // this.attributesService.add(this.selectedAttribute).subscribe(data => {
      //   console.log(data);
      //   this.selectedAttribute = data;
      //   this.attrObjectTypeService.add({
      //     attrId: this.selectedAttribute.attrId,
      //     objectTypeId: this.selectedObjectType.objectTypeId,
      //     options: 0
      //   }).subscribe(data => {
      //     this.loadAOTS(this.selectedObjectType.objectTypeId);
      //     console.log(data);
      //   })
      // });
    } else {
      console.log("Fill parameters");
      console.log(this.selectedAttribute);
    }
  }

  validateAttributeParameters(): boolean {
    if (!this.selectedAttribute || !this.selectedAttribute.name) {
      console.log("Uncorrected attribute name!");
      return false;
    }
    if (!this.selectedAttributeGroup || !this.selectedAttributeGroup.attrGroupId) {
      console.log("Uncorrected attr group!");
      return false;
    }
    if (this.selectedAttribute.attrTypeId == 4 || this.selectedAttribute.attrTypeId == 5)
      if (!this.selectedAttrTypeDef || !this.selectedAttrTypeDef.attrTypeDefId) {
        console.log("Uncorrected attr type def!");
        return false;
      }
    return true;
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
    this._parent.loadAOTS(this._parent.selectedObjectType.objectTypeId);
  }

  ngOnInit() {
    if (this.level == 0) this.show = true;
  }

}
