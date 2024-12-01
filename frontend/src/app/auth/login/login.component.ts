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
				this.jwtResponse = response;
				localStorage.setItem('accessToken', this.jwtResponse.accessToken.toString());
				localStorage.setItem('refreshToken', this.jwtResponse.refreshToken.toString());
			},
			error: (error) => {
				this.errorMessage = error;
			}
		});
	}

}
