import { Component } from '@angular/core';
import { Router, RouterOutlet } from '@angular/router';
import { HeaderComponent } from "./component/header/header.component";
import { FooterComponent } from './component/footer/footer.component';
import { NgIf } from '@angular/common';
import { AuthService } from './services/auth.service';
import { DashboardComponent } from './component/dashboard/dashboard.component';
import { AlertService } from './services/alert.service';
import { AlertComponent } from './component/alert/alert/alert.component';

@Component({
	selector: 'app-root',
	standalone: true,
    imports: [RouterOutlet ,HeaderComponent, FooterComponent, NgIf, DashboardComponent, AlertComponent],
	templateUrl: './app.component.html',
	styleUrl: './app.component.css'
})
export class AppComponent {
	title = 'BlogSphere';
	constructor(
        private authService: AuthService,
        private router: Router,
    ){}

	get isAuthenticated(): boolean{
		return this.authService.isLoggedIn();
	}
	get isDashboardRoute(): boolean {
		return this.router.url.startsWith('/dashboard');
	}
	get isLoginRoute(): boolean {
		return this.router.url.startsWith('/login') || this.router.url.startsWith('/register');
	}
}
