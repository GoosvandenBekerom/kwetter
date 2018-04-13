import {User} from "./User";
import {Hashtag} from "./Hashtag";
import {Mention} from "./Mention";

export class Kweet {
  constructor(
    public id: number,
    public owner: User,
    public message: string,
    public likes: User[],
    public likeCount: number,
    public hashtags: Hashtag[],
    public mentions: Mention[],
    public created: string) {}
}
