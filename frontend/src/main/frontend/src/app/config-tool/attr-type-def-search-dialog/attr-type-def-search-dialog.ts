import {Component, Inject} from '@angular/core';
import {MatDialog, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material';
import {AttrTypeDefService} from "../../shared/attr-type-def/attr-type-def.service";

@Component({
  selector: 'attr-type-def-search-dialog',
  templateUrl: 'attr-type-def-search-dialog.html',
})
export class AttrTypeDefSearchDialog {

  nameForSearch: string;
  foundedAttrTypeDefs: Array<any>;

  constructor(public dialogRef: MatDialogRef<AttrTypeDefSearchDialog>,
              @Inject(MAT_DIALOG_DATA) public data: any,
              private attrTypeDefService: AttrTypeDefService) {
  }

  selectAttrTypeDefByName(): void {
    if (this.nameForSearch.length > 0) {
      this.attrTypeDefService.getAllByName(this.nameForSearch).subscribe(data => {
        console.log(data);
        this.foundedAttrTypeDefs = data;
      });
    }
  }

  onNoClick(): void {
    this.dialogRef.close();
  }

}
