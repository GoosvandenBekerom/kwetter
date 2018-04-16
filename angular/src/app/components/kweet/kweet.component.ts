import {Component, ElementRef, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {Kweet} from "../../models/Kweet";
import * as moment from 'moment';
import {AuthService} from "../../services/auth.service";
import {KweetService} from "../../services/kweet.service";
import {User} from "../../models/User";
import {ActivatedRoute, Router} from "@angular/router";

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
    this._kweet = kweet
  }
  get kweet() {return this._kweet}

  constructor(
    private auth: AuthService,
    private kweetService: KweetService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit() {
    this.loggedInUser = this.route.snapshot.data.user
    this.liked = this.isLikedByLoggedInUser()

    // Hashtags
    for (let i = 0; i < this._kweet.hashtags.length; i++) {
      const tag = this._kweet.hashtags[i].value
      this._kweet.message = this._kweet.message
        .replace(
          `#${tag}`,
          `<a href="/hashtag/${tag}" class="text-info" title="Find more posts tagged with #${tag}">#${tag}</a>`
        )
    }

    // Mentions
    for (let i = 0; i < this._kweet.mentions.length; i++) {
      const mention = this._kweet.mentions[i].user.username
      this._kweet.message = this._kweet.message
        .replace(
          `@${mention}`,
          `<a href="/profile/${mention}" class="text-info" title="Go to @${mention}\'s profile">@${mention}</a>`
        )
    }

    document.onclick = (e) => {
      const element = e.target as Element
      if (element && element.tagName == 'A') {
        const routerUrl = element.href.replace(/^(http|https):\/\//, '').replace(window.location.host, '')
        this.router.navigate([routerUrl])
        return false
      }
    };
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
