import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {Kweet} from "../../models/Kweet";
import * as moment from 'moment';
import {AuthService} from "../../services/auth.service";
import {KweetService} from "../../services/kweet.service";

@Component({
  selector: 'app-kweet',
  templateUrl: './kweet.component.html',
  styleUrls: ['./kweet.component.css']
})
export class KweetComponent implements OnInit {
  @Input() isLast: boolean
  @Output() onDelete = new EventEmitter<Kweet>()

  liked = false;
  ownedByLoggedInUser: boolean

  private _kweet: Kweet
  @Input('kweet') set kweet(kweet: Kweet) {
    kweet.message = kweet.message
      .replace(/#(\S+)/g,'<a href="#" class="text-info" title="Find more posts tagged with #$1">#$1</a>')
      .replace(/@(\S+)/g,'<a href="#" class="text-info" title="Go to #$1\'s profile">@$1</a>')

    this._kweet = kweet
  }
  get kweet() {return this._kweet}

  constructor(
    private auth: AuthService,
    private kweetService: KweetService
  ) { }

  ngOnInit() {
    this.ownedByLoggedInUser = this.auth.getLoggedInUsername() == this.kweet.owner.username
  }

  public getCreatedRelative() {
    return moment(this.kweet.created).fromNow()
  }

  onLikeClick() {
    this.liked = !this.liked;
  }

  onDeleteClick() {
    this.kweetService.deleteKweet(this.kweet).subscribe(() => this.onDelete.emit(this.kweet))
  }
}
