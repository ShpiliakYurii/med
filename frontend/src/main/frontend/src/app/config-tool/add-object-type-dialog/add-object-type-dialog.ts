import {Component, Inject} from '@angular/core';
import {MatDialog, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material';

@Component({
  selector: 'add-object-type-dialog',
  templateUrl: 'add-object-type-dialog.html',
  styleUrls: ['add-object-type-dialog.css']
})
export class AddObjectTypeDialog {

  constructor(public dialogRef: MatDialogRef<AddObjectTypeDialog>,
              @Inject(MAT_DIALOG_DATA) public data: any) {
    console.log(data);
  }

  onNoClick(): void {
    this.dialogRef.close();
  }

}
