import { Component } from '@angular/core';
import {UntypedFormControl, UntypedFormGroup, Validators} from "@angular/forms";
import { Router } from "@angular/router";
import { AuthenticationService } from "../authentication/authentication.service";
import { Credentials } from "../entities/credentials.entity";
import { Role } from "../entities/role.enum";


@Component({
  selector: 'ms-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})

export class LoginComponent {
  credentialsFormGroup: UntypedFormGroup;

  constructor(
    private authenticationService: AuthenticationService,
    private router: Router
  ) {
    this.credentialsFormGroup = new UntypedFormGroup({
      login: new UntypedFormControl('', Validators.minLength(1)),
      password: new UntypedFormControl('', Validators.minLength(6))
    });
  }

  public doLogin(): void {
    this.authenticationService.login(new Credentials(
      this.credentialsFormGroup.get('login')!.value,
      this.credentialsFormGroup.get('password')!.value
    )).subscribe(
      credentials => {
        switch (credentials.role) {
          case Role.ADMIN:
            this.router.navigate(['home']);
            break;
          case Role.USER:
            this.router.navigate(['home']);
            break;
          default:
            console.error(`Unknown role: ${credentials.role}`);
        }
      },
    );
  }

}
