import {User} from "./User";

export class Mention {
  constructor(
    public id: number,
    public user: User,
    public seen: boolean
  ) {}
}
