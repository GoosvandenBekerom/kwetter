import {User} from "./User";

export class Kweet {
  constructor(public id: number, public owner: User, public message: string, public likeCount: number, public created: string) {}
}
