export class User{
  constructor(public username: string, public fullName: string, public created: Date) {}

  static from(object: any) : User {
    if (object.username == undefined || object.fullName == undefined || object.created == undefined) return null

    return new User(object.username, object.fullName, object.created)
  }
}
