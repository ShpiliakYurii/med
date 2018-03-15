import {Component, Inject} from '@angular/core';
import {MatDialog, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material';
import {AttrGroupsService} from "../../shared/attr-groups/attr-groups.service";
import {SelectionModel} from "@angular/cdk/collections";

@Component({
  selector: 'attr-group-search-dialog',
  templateUrl: 'attr-group-search-dialog.html',
  styleUrls:['attr-group-search-dialog.css', '../general-dialog-styles.css']
})
export class AttrGroupSearchDialog {

  nameForSearch: string = "";
  foundedAttrGroups: Array<any> = [];
  displayedColumns = ['select', 'ID', 'Name', 'Subgroup'];
  selection = new SelectionModel<any>(true, []);

  constructor(public dialogRef: MatDialogRef<AttrGroupSearchDialog>,
              @Inject(MAT_DIALOG_DATA) public data: any,
              private attrGroupsService: AttrGroupsService) {
  }

  selectAttrGroupsByName(): void {
    if (this.nameForSearch.length > 0) {
      this.attrGroupsService.getAllByName(this.nameForSearch).subscribe(data => {
        this.foundedAttrGroups = data;
      });
    }
  }

  toggle(row: any):void{
    if(!this.selection.isSelected(row)){
      this.selection.clear();
    }
    this.selection.toggle(row);
  }

  onNoClick(): void {
    this.dialogRef.close();
  }

}
