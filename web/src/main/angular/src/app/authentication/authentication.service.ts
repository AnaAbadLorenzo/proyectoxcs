import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable, of} from 'rxjs';
import {environment} from '../../environments/environment';
import {map} from 'rxjs/operators';
import {UserAndRole} from './user-and-role.model';
import {Role} from '../entities/role.enum';
import {Credentials} from '../entities/credentials.entity';

const CREDENTIALS_ITEM = 'credentials';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {
  constructor(
    private readonly http: HttpClient
  ) {
  }

  public hasCredentials(): boolean {
    return localStorage.getItem(CREDENTIALS_ITEM) !== null;
  }

  public hasRole(role: Role): boolean {
    if (this.hasCredentials()) {
      return this.getCredentials()!.role === role;
    } else {
      return false;
    }
  }

  public getCredentials(): Credentials | undefined {
    const item = localStorage.getItem(CREDENTIALS_ITEM);

    if (item) {
      const stored = JSON.parse(item);
      return new Credentials(stored.username, stored.password, stored.role);
    } else {
      return undefined;
    }
  }

  public login(credentials: Credentials): Observable<Credentials> {
    if (this.hasCredentials()) {
      return of(this.getCredentials() as Credentials);
    } else {
      const headers = new HttpHeaders({
        Authorization: 'Basic ' + credentials.asToken(),
        Accept: 'application/json'
      });

      return this.http.get<UserAndRole>(`${environment.api}/microstory/recent`, {headers})
        .pipe(
          map(userAndRole => {
            const credentialsWithRole = new Credentials(
              credentials.username, credentials.password, userAndRole.role
            );

            localStorage.setItem(CREDENTIALS_ITEM, JSON.stringify(credentialsWithRole));

            return credentialsWithRole;
          })
        );
    }
  }

  public logout(): void {
    localStorage.removeItem(CREDENTIALS_ITEM);
  }
}
