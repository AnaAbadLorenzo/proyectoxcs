import {Injectable} from '@angular/core';
import { ActivatedRouteSnapshot, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import {Observable} from 'rxjs';
import {AuthenticationService} from './authentication.service';
import {Role} from '../entities/role.enum';

@Injectable({
  providedIn: 'root'
})
export class AdminGuard  {
  constructor(
    private authentication: AuthenticationService,
    private router: Router
  ) {
  }

  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
    return this.checkRole();
  }

  private checkRole(): boolean {
    if (this.authentication.hasRole(Role.ADMIN)) {
      return true;
    } else {
      this.router.navigate(['/']);

      return false;
    }
  }
}
