import { Component } from '@angular/core';
import { Router } from '@angular/router';
import {AuthenticationService} from "../../authentication/authentication.service";

@Component({
  selector: 'ms-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent {
  mostrarInput = false;
  searchValue: string = '';

  constructor(private router: Router, private authenticationService: AuthenticationService) {}

  /**
   * Method that handles the toggling of the search input field.
   *
   * @param event The input event (optional).
   */

  toggleInput(event?: Event) {
    if (event) {
      event.stopPropagation();
    }
    if (!this.mostrarInput && this.searchValue.trim() !== '') {
      this.router.navigate(['/search', this.searchValue.trim()]);
    }
    this.mostrarInput = !this.mostrarInput;
  }

  /**
   * Method that clears the search input and hides it.
   */

  limpiarInputYCerrar() {
    this.searchValue = '';
    this.mostrarInput = false;
  }

  public doLogout(): void {
    this.authenticationService.logout();
    this.router.navigate(['/']);
  }

  public getCurrentUser(): string | undefined{
    return this.authenticationService.hasCredentials() ? this.authenticationService.getCredentials()!.username : undefined;
  }

  public isLogged(): boolean {
    return this.authenticationService.hasCredentials();
  }
}
