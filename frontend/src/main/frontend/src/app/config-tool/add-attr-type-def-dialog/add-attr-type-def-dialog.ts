import {Component, Inject} from '@angular/core';
import {MatDialog, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material';

@Component({
  selector: 'add-attr-type-def-dialog',
  templateUrl: 'add-attr-type-def-dialog.html',
  styleUrls: ['add-attr-type-def-dialog.css', '../general-dialog-styles.css']
})
export class AddAttrTypeDefDialog {


  constructor(public dialogRef: MatDialogRef<AddAttrTypeDefDialog>,
              @Inject(MAT_DIALOG_DATA) public data: any) {
  }

  onNoClick(): void {
    this.dialogRef.close();
  }

  createNewListValue(): void {
    this.data.listValues.push({
      value: 'New List Value ' + this.data.listValues.length,
      listValueId: undefined,
    });
  }

  deleteListValue(i: number): void {
    this.data.listValues.splice(i, 1);
  }

}
