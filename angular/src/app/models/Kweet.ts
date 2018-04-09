import {User} from "./User";

export class Kweet {
  constructor(public id: number, public owner: User, public message: string, public likeCount: number, public created: Date) {}

  static from(x: any) {
    if (x.id == undefined || x.owner == undefined || x.message == undefined || x.likeCount == undefined
      || x.created == undefined) return null

    return new Kweet(x.id, x.owner, x.message, x.likeCount, x.created)
  }
}
