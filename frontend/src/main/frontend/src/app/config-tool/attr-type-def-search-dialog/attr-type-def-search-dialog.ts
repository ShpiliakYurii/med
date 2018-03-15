import {Component, Inject} from '@angular/core';
import {MatDialog, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material';
import {AttrTypeDefService} from "../../shared/attr-type-def/attr-type-def.service";
import {SelectionModel} from "@angular/cdk/collections";

@Component({
  selector: 'attr-type-def-search-dialog',
  templateUrl: 'attr-type-def-search-dialog.html',
  styleUrls: ['../general-dialog-styles.css', 'attr-type-def-search-dialog.css']
})
export class AttrTypeDefSearchDialog {

  nameForSearch: string;
  foundedAttrTypeDefs: Array<any> = [];
  displayedColumns = ['select', 'Name', 'attrTypeDefID'];
  selection = new SelectionModel<any>(true, []);

  constructor(public dialogRef: MatDialogRef<AttrTypeDefSearchDialog>,
              @Inject(MAT_DIALOG_DATA) public data: any,
              private attrTypeDefService: AttrTypeDefService) {
  }

  selectAttrTypeDefByName(): void {
    if (this.nameForSearch.length > 0) {
      this.attrTypeDefService.getAllByName(this.nameForSearch).subscribe(data => {
        this.foundedAttrTypeDefs = data;
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
