import { NgForOf, NgIf } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { RegisterRequest } from '../../models/register-request';
import { AuthService } from '../../services/auth.service';
import { Router, RouterLink } from '@angular/router';
import { ErrorResponse } from '../../models/error-response';

@Component({
	selector: 'auth-register',
	standalone: true,
	imports: [FormsModule, RouterLink],
	templateUrl: './register.component.html',
	styleUrl: './register.component.css'
})
export class RegisterComponent {
	registerRequest: RegisterRequest = {
		username: '',
		email: '',
		password: '',
		firstName: '',
		lastName: '',
		roles: [],
		profilePicture: '',
		bio: ''
	};
	errorMessage: ErrorResponse | null = null;

	constructor(private authService: AuthService, private router: Router) { }

	register(): void{
		this.authService.register(this.registerRequest).subscribe({
			next: () => {
				this.router.navigate(['']);
			},
			error: (error) => {
				this.errorMessage = error;
				console.log(error);
			}
		});
	}
}
