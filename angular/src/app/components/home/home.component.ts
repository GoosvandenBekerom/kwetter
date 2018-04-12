import { Component, OnInit } from '@angular/core';
import {Kweet} from "../../models/Kweet";
import {User} from "../../models/User";
import {ActivatedRoute} from "@angular/router";
import {TimelineService} from "../../services/timeline.service";
import {Hashtag} from "../../models/Hashtag";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  user: User
  kweets: Kweet[]
  topHashtags: Hashtag[]

  constructor(
    private route: ActivatedRoute,
    private timeline: TimelineService
  ) {}

  ngOnInit() {
    this.user = this.route.snapshot.data.user
    this.topHashtags = this.route.snapshot.data.topHashtags
    console.log(this.topHashtags)

    this.timeline.forUser().subscribe(
      timeline => this.kweets = timeline,
      err => console.log(err.error.message)
    )
  }

  updateTimeline(kweet: Kweet) {
    this.kweets.unshift(kweet)
    this.updateHashtagTop(kweet)
    console.log(this.topHashtags)
  }

  updateHashtagTop(kweet: Kweet) {
    for (let i = 0; i < kweet.hashtags.length; i++) {
      let tag = this.topHashtags.find(h => h.value == kweet.hashtags[i].value)
      if (tag) {
        tag.count++
        break;
      }
    }
  }
}
