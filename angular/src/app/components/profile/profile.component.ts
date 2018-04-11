import { Component, OnInit } from '@angular/core';
import {Kweet} from "../../models/Kweet";
import {TimelineService} from "../../services/timeline.service";

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {
  private kweets: Kweet[]

  constructor(private timeline: TimelineService) { }

  ngOnInit() {
    const username = "..."
    this.timeline.forUser(username).subscribe(data => {

    })
  }
}
