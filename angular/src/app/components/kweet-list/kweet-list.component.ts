import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {Kweet} from "../../models/Kweet";
import {HashtagService} from "../../services/hashtag.service";

import {Observable} from "rxjs/Observable";
import "rxjs/add/operator/map";
import 'rxjs/add/observable/of';
import "rxjs/add/operator/switchMap";

@Component({
  selector: 'app-kweet-list',
  templateUrl: './kweet-list.component.html',
  styleUrls: ['./kweet-list.component.css']
})
export class KweetListComponent implements OnInit {
  listTitle: string
  kweets: Kweet[]

  constructor(
    private route: ActivatedRoute,
    private hashtagService: HashtagService
  ) { }

  ngOnInit() {
    this.route.params
      .map(params => {
        if (params.hashtag) return `#${params.hashtag}`
        else return 'nothing'
        // todo: add other possibilities
      })
      .switchMap(query => {
        this.listTitle = query
        return query.startsWith('#')
          ? this.hashtagService.getTimelineForTag(query.replace('#', ''))
          : Observable.of([])
      })
      .subscribe(kweets => this.kweets = kweets)
  }
}
