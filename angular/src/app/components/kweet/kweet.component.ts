import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {Kweet} from "../../models/Kweet";
import * as moment from 'moment';
import {AuthService} from "../../services/auth.service";
import {KweetService} from "../../services/kweet.service";
import {User} from "../../models/User";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-kweet',
  templateUrl: './kweet.component.html',
  styleUrls: ['./kweet.component.css']
})
export class KweetComponent implements OnInit {
  @Input() isLast: boolean
  @Output() onDelete = new EventEmitter<Kweet>()

  private loggedInUser: User
  liked: boolean

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
    private kweetService: KweetService,
    private route: ActivatedRoute
  ) { }

  ngOnInit() {
    this.loggedInUser = this.route.snapshot.data.user
    this.liked = this.isLikedByLoggedInUser()
  }

  getCreatedRelative() {
    return moment(this.kweet.created).fromNow()
  }

  ownedByLoggedInUser() : boolean {
    return this.loggedInUser.username == this.kweet.owner.username
  }

  onLikeClick() {
    this.kweetService.toggleLike(this.kweet).subscribe(() => {
      if (this.liked) this.removeLike()
      else this.addLike()
    })
  }

  onDeleteClick() {
    this.kweetService.deleteKweet(this.kweet).subscribe(() => this.onDelete.emit(this.kweet))
  }

  private addLike() {
    this.liked = true
    this.kweet.likes.push(this.loggedInUser)
    this.kweet.likeCount++
  }

  private removeLike() {
    this.liked = false
    let index = -1
    for (let i = 0; i < this.kweet.likes.length; i++) {
      if (this.kweet.likes[i].username == this.loggedInUser.username) {
        index = i
        break;
      }
    }
    this.kweet.likes.splice(index, 1)
    this.kweet.likeCount--
  }

  private isLikedByLoggedInUser() : boolean {
    for (let i = 0; i < this.kweet.likes.length; i++) {
      if (this.kweet.likes[i].username == this.loggedInUser.username) return true
    }
    return false
  }
}
