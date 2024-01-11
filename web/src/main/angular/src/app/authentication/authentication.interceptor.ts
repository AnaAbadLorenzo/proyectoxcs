import {Injectable} from '@angular/core';
import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from '@angular/common/http';
import {Observable} from 'rxjs';
import {AuthenticationService} from './authentication.service';

@Injectable()
export class AuthenticationInterceptor implements HttpInterceptor {
  constructor(public authenticationService: AuthenticationService) {
  }

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    if (this.authenticationService.hasCredentials()) {
      request = request.clone({
        setHeaders: {
          Authorization: `Basic ${this.authenticationService.getCredentials()!.asToken()}`
        }
      });
    }

    return next.handle(request);
  }
}
