import { Component, Injectable, OnInit } from '@angular/core';
import { LoginRequest } from '../../models/login-request';
import { JwtResponse } from '../../models/jwt-response';
import { ErrorResponse } from '../../models/error-response';
import { AuthService } from '../../services/auth.service';
import { Router } from '@angular/router';
import { NgIf } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
	selector: 'auth-login',
	standalone: true,
	imports: [FormsModule, NgIf],
	templateUrl: './login.component.html',
	styleUrl: './login.component.css',
})

export class LoginComponent {
	loginRequest: LoginRequest = { username: '', password: '' };
	jwtResponse: JwtResponse | null = null;
	errorMessage: ErrorResponse | null = null;

	constructor(private authService: AuthService, private router: Router) { }

	login(): void {
		this.authService.login(this.loginRequest).subscribe({
			next: (response) => {
				console.log(response);
				this.jwtResponse = response;

				if (this.jwtResponse.accessToken && this.jwtResponse.refreshToken) {
					localStorage.setItem('accessToken', this.jwtResponse.accessToken.valueOf());
					localStorage.setItem('refreshToken', this.jwtResponse.refreshToken.valueOf());
				} else {
					console.error('accessToken or refreshToken is missing!');
				}

				this.router.navigate(['/register']);
			},
			error: (error) => {
				this.errorMessage = error;
			}
		});
	}

}
