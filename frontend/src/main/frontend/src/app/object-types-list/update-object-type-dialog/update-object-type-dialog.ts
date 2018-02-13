import {Component, Inject} from '@angular/core';
import {MatDialog, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material';

@Component({
  selector: 'update-object-type-dialog',
  templateUrl: 'update-object-type-dialog.html'
})
export class UpdateObjectTypeDialog {

  constructor(public dialogRef: MatDialogRef<UpdateObjectTypeDialog>,
              @Inject(MAT_DIALOG_DATA) public data: any) {
    console.log(data);
  }

  onNoClick(): void {
    this.dialogRef.close();
  }

}
