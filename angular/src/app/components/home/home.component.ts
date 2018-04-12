import { Component, OnInit } from '@angular/core';
import {Kweet} from "../../models/Kweet";
import {User} from "../../models/User";
import {ActivatedRoute} from "@angular/router";
import {TimelineService} from "../../services/timeline.service";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  user: User
  kweets: Kweet[]

  constructor(
    private route: ActivatedRoute,
    private timeline: TimelineService
  ) {}

  ngOnInit() {
    this.user = this.route.snapshot.data.user

    this.timeline.forUser().subscribe(
      timeline => this.kweets = timeline,
      err => console.log(err.error.message)
    )
  }

  updateTimeline(kweet: Kweet) {
    this.kweets.unshift(kweet)
  }
}
