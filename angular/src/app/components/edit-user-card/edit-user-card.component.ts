import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {User} from "../../models/User";
import {UserService} from "../../services/user.service";
import {FlashService} from "../../services/flash.service";

@Component({
  selector: 'app-edit-user-card',
  templateUrl: './edit-user-card.component.html',
  styleUrls: ['./edit-user-card.component.css']
})
export class EditUserCardComponent implements OnInit {
  @Output() onDone = new EventEmitter()
  @Input() user: User

  originalFullName: string

  oldPass: string
  newPass: string
  newPassAgain: string

  constructor(
    private userService: UserService,
    private flash: FlashService
  ) { }

  ngOnInit() {
    this.originalFullName = this.user.fullName
  }

  onCloseEdit() {
    this.user.fullName = this.originalFullName
    this.onDone.emit()
  }

  onDetailSubmit() {
    if (this.user.fullName !== this.originalFullName) {
      this.originalFullName = this.user.fullName
      this.userService.updateFullname(this.user).subscribe(() => this.flash.success('Successfully updated user details'))
    }
  }

  onPasswordSubmit() {
    if (this.oldPass && this.newPass && this.newPassAgain) {
      if (this.newPass === this.newPassAgain) {
        this.userService.updatePassword(this.oldPass, this.newPass).subscribe(() => {
          this.oldPass = this.newPass = this.newPassAgain = ''
          this.flash.success('Successfully updated password')
        })
      } else {
        this.flash.error('The new passwords do not match.')
      }
    } else if (this.oldPass || this.newPass || this.newPassAgain) {
      this.flash.warn('Please fill out all password related fields in order to update your password.')
    }
  }
}
