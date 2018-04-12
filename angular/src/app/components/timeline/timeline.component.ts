import {Component, Input, OnInit} from '@angular/core';
import {Kweet} from "../../models/Kweet";

@Component({
  selector: 'app-timeline',
  templateUrl: './timeline.component.html',
  styleUrls: ['./timeline.component.css']
})
export class TimelineComponent implements OnInit {
  @Input() kweets: Kweet[]

  constructor() { }
  ngOnInit() { }

  onKweetDeleted(kweet: Kweet) {
    const index = this.kweets.indexOf(kweet, 0)
    if (index > -1)
      this.kweets.splice(index, 1)
  }
}
