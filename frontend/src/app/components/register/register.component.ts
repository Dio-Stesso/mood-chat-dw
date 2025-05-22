/* register.component.ts
 * полная, «чистая» версия файла
 * — редирект после успешной регистрации через Router
 * — обработка ошибки      → выводим errorMessage
 * — никаких «ts-ignore», inline-redirectов и Storage
 */

import { Component } from '@angular/core';
import { Router } from '@angular/router';

import { AuthenticationService } from '../../service/AuthenticationService';
import { RegisterUser }           from '../model/RegisterUser';

@Component({
  selector   : 'app-register',
  templateUrl: './register.component.html',
  styleUrls  : ['./register.component.css']
})
export class RegisterComponent {

  /* ======= поля, связанные с формой =================================== */
  username?: string;
  email?   : string;
  password?: string;
  repeatPassword?: string;

  /* ======= сообщения для пользователя ================================= */
  errorMessage?: string;

  constructor(
    private authService: AuthenticationService,
    private router     : Router
  ) {}

  /* ==================================================================== */
  register(): void {
    const dto: RegisterUser = {
      username       : this.username ?? '',
      email          : this.email    ?? '',
      password       : this.password ?? '',
      repeatPassword : this.repeatPassword ?? ''
    };

    this.authService.postRegister(dto).subscribe({
      next : () => this.router.navigate(['/login']),
      error: () => (this.errorMessage = 'Incorrect data!')
    });
  }

  toLogin(): void {
    this.router.navigate(['/login']);
  }
}
