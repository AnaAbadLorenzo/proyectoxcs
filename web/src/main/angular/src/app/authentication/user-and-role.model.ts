import {Role} from '../entities/role.enum';

export class UserAndRole {
  public constructor(
    public readonly login: string,
    public readonly role: Role
  ) {
  }
}
