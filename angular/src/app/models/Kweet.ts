import {User} from "./User";
import {Hashtag} from "./Hashtag";

export class Kweet {
  constructor(
    public id: number,
    public owner: User,
    public message: string,
    public likes: User[],
    public likeCount: number,
    public hashtags: Hashtag[],
    public created: string) {}
}
