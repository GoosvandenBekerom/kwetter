import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {User} from "../../models/User";
import {UserService} from "../../services/user.service";

@Component({
  selector: 'app-edit-user-card',
  templateUrl: './edit-user-card.component.html',
  styleUrls: ['./edit-user-card.component.css']
})
export class EditUserCardComponent implements OnInit {
  @Output() onDone = new EventEmitter()
  @Input() user: User

  originalFullName: string

  constructor(private userService: UserService) { }

  ngOnInit() {
    this.originalFullName = this.user.fullName
  }

  onCancelEdit() {
    this.user.fullName = this.originalFullName
    this.onDone.emit()
  }

  onUpdateClick() {
    this.originalFullName = this.user.fullName
    this.userService.updateFullname(this.user).subscribe(() => this.onDone.emit())
  }
}
