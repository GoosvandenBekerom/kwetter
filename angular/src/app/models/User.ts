import {Link} from "./Link";

export class User{
  constructor(
    public username: string,
    public fullName: string,
    public kweetCount: number,
    public followerCount: number,
    public followingCount: number,
    public created: string,
    public links: Link[]
  ) {}
}
