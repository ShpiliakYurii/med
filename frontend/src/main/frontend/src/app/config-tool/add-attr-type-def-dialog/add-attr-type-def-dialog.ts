import {Component, Inject} from '@angular/core';
import {MatDialog, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material';

@Component({
  selector: 'add-attr-type-def-dialog',
  templateUrl: 'add-attr-type-def-dialog.html',
})
export class AddAttrTypeDefDialog{

  constructor(public dialogRef: MatDialogRef<AddAttrTypeDefDialog>,
              @Inject(MAT_DIALOG_DATA) public data: any) {
  }

  onNoClick(): void {
    this.dialogRef.close();
  }

}
