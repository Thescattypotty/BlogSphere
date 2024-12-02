import { Component, OnInit } from '@angular/core';
import { RouterLink } from '@angular/router';
import { TagService } from '../../services/tag.service';
import { TagResponse } from '../../models/tag-response';
import { ErrorResponse } from '../../models/error-response';
import { NgFor, NgIf } from '@angular/common';
import { catchError, of } from 'rxjs';

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

	constructor(private tagService: TagService){ }

	ngOnInit(): void {
		this.tagService.getAllTags().pipe(
			catchError(error => {
				console.error('Error fetching tags:', error);
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
