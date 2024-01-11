import {Role} from './role.enum';

export class Credentials {
  public constructor(
    public username: string,
    public password: string,
    public role?: Role
  ) {
  }

  public asToken(): string {
    return btoa(this.username + ':' + this.password);
  }
}
