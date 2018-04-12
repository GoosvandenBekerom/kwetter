import {Component, Input, OnInit} from '@angular/core';
import {Hashtag} from "../../models/Hashtag";

@Component({
  selector: 'app-trending-card',
  templateUrl: './trending-card.component.html',
  styleUrls: ['./trending-card.component.css']
})
export class TrendingCardComponent implements OnInit {
  @Input() hashtags: Hashtag[]

  constructor() { }
  ngOnInit() {}
}
