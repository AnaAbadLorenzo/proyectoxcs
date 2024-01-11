import {Injectable} from '@angular/core';
import { ActivatedRouteSnapshot, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import {Observable} from 'rxjs';
import {AuthenticationService} from './authentication.service';
import {Role} from '../entities/role.enum';

@Injectable({
  providedIn: 'root'
})
export class NotAuthenticatedGuard  {
  constructor(
    private readonly authenticationService: AuthenticationService,
    private readonly router: Router
  ) {
  }

  canActivate(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
    if (this.authenticationService.hasCredentials()) {
      if (this.authenticationService.hasRole(Role.ADMIN)) {
        this.router.navigate(['/home']);
      } else if (this.authenticationService.hasRole(Role.USER)) {
        this.router.navigate(['/home']);
      }

      return false;
    } else {
      return true;
    }
  }
}
