import { Injectable } from '@angular/core';
import {Subject} from "rxjs/Subject";
import {WebsocketService} from "./websocket.service";
import {BASE_URL_WEBSOCKET} from "../utils/constants";
import {Kweet} from "../models/Kweet";

export interface KweetMessage {
  kweet: Kweet
}

@Injectable()
export class KweetSubjectService {
  public kweets: Subject<KweetMessage>;

  constructor(private wsService: WebsocketService) {}

  connect(username: string) {
    this.kweets = <Subject<KweetMessage>>this.wsService
      .connect(`${BASE_URL_WEBSOCKET}/${username}`)
      .map((response: MessageEvent): KweetMessage => {
        return response.data
        //let data = JSON.parse(response.data)
        //return { kweet: data.kweet }
      });
  }
}
