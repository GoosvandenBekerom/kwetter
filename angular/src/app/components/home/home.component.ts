import { Component, OnInit } from '@angular/core';
import {Kweet} from "../../models/Kweet";
import {User} from "../../models/User";
import {ActivatedRoute} from "@angular/router";
import {TimelineService} from "../../services/timeline.service";
import {Hashtag} from "../../models/Hashtag";
import {KweetSubjectService} from "../../services/kweet-subject.service";

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
    private timeline: TimelineService,
    private kweetSubject: KweetSubjectService
  ) {}

  ngOnInit() {
    this.user = this.route.snapshot.data.user
    this.topHashtags = this.route.snapshot.data.topHashtags

    this.timeline.forUser().subscribe(
      timeline => this.kweets = timeline,
      err => console.log(err.error.message)
    )

    this.initializeWebSocketConnection()
  }

  initializeWebSocketConnection() {
    this.kweetSubject.connect(this.user.username)
    this.kweetSubject.kweets.subscribe((kweet: Kweet) => {
      console.log(kweet)
      this.updateTimeline(kweet)
    })
  }

  updateTimeline(kweet: Kweet) {
    this.kweets.unshift(kweet)
    this.updateHashtagTop(kweet)
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
