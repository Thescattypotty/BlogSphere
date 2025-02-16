import { Component, OnInit } from '@angular/core';
import { Router, RouterLink } from '@angular/router';
import { TagService } from '../../services/tag.service';
import { TagResponse } from '../../models/tag-response';
import { ErrorResponse } from '../../models/error-response';
import { NgFor, NgIf } from '@angular/common';
import { catchError, of } from 'rxjs';
import { AuthService } from '../../services/auth.service';
import { AlertService } from '../../services/alert.service';

@Component({
  selector: 'app-header',
  standalone: true,
  imports: [RouterLink, NgFor, NgIf],
  templateUrl: './header.component.html',
  styleUrl: './header.component.css'
})
export class HeaderComponent implements OnInit{
	tags: TagResponse[] = [];
	errorMessage: ErrorResponse | null = null;
	isLoading: boolean = true;

	constructor(
        private tagService: TagService,
        private authService: AuthService,
        private router: Router,
        private alertService: AlertService
    ){ }

	get isAuthenticated(): boolean{
		return this.authService.isLoggedIn();
	}
	logout(): void{
		this.authService.logout();
	}
    navigateToTag(id: String){
        this.router.navigate(['tags', id]);
    }

	ngOnInit(): void {
		this.tagService.getAllTags().pipe(
			catchError(error => {
				this.alertService.add('Error fetching tags');
				this.errorMessage = error.message || 'Failed to load tags';
				this.isLoading = false;
				return of([]);
			})
		).subscribe({
			next: (response) => {
				this.tags = response;
				this.isLoading = false;
			}
		});
	}
}
