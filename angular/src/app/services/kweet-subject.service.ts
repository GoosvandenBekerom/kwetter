import { Injectable } from '@angular/core'
import {Subject} from "rxjs/Subject"
import {WebsocketService} from "./websocket.service"
import {BASE_URL_WEBSOCKET} from "../utils/constants"
import {Kweet} from "../models/Kweet";
import "rxjs/add/operator/filter";

@Injectable()
export class KweetSubjectService {
  public kweets: Subject<Kweet>

  constructor(private wsService: WebsocketService) {}

  connect(username: string) {
    this.kweets = <Subject<Kweet>>this.wsService
      .connect(`${BASE_URL_WEBSOCKET}/${username}`)
      .map((response: MessageEvent): Kweet => JSON.parse(response.data))
  }
}
