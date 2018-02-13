import {Component, Inject} from '@angular/core';
import {MatDialog, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material';

@Component({
  selector: 'add-attr-group-dialog',
  templateUrl: 'add-attr-group-dialog.html',
})
export class AddAttrGroupDialog{

  constructor(public dialogRef: MatDialogRef<AddAttrGroupDialog>,
              @Inject(MAT_DIALOG_DATA) public data: any) {
  }

  onNoClick(): void {
    this.dialogRef.close();
  }

}
