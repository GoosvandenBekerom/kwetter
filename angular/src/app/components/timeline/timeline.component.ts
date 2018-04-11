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
}
