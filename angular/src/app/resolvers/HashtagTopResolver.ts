import {ActivatedRouteSnapshot, Resolve, RouterStateSnapshot} from "@angular/router";
import {Observable} from "rxjs/Observable";
import {Injectable} from "@angular/core";
import {Hashtag} from "../models/Hashtag";
import {HashtagService} from "../services/hashtag.service";

@Injectable()
export class HashtagTopResolver implements Resolve<Hashtag[]> {
  constructor(private service: HashtagService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Hashtag[]> {
    return this.service.getTopHashtags()
  }
}
