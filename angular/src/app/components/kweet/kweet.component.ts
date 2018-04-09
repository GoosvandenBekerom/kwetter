import {Component, Input, OnInit} from '@angular/core';
import {Kweet} from "../../models/Kweet";
import * as moment from 'moment';

@Component({
  selector: 'app-kweet',
  templateUrl: './kweet.component.html',
  styleUrls: ['./kweet.component.css']
})
export class KweetComponent implements OnInit {
  private _kweet: Kweet
  @Input('kweet') set kweet(kweet: Kweet) {
    kweet.message = kweet.message
      .replace(/#(\S+)/g,'<a href="#" class="text-info" title="Find more posts tagged with #$1">#$1</a>')
      .replace(/@(\S+)/g,'<a href="#" class="text-info" title="Go to #$1\'s profile">@$1</a>')

    this._kweet = kweet
  }
  get kweet() {return this._kweet}

  @Input() isLast: boolean

  constructor() { }

  ngOnInit() {}

  public getCreatedRelative() {
    return moment(this.kweet.created).fromNow()
  }
}
