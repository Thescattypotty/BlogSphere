import { Component, OnInit } from '@angular/core';
import { CardPostComponent } from "../component/card-post/card-post.component";
import { PostService } from '../services/post.service';
import { PostResponse } from '../models/post-response';

@Component({
	selector: 'app-home',
	standalone: true,
	imports: [CardPostComponent],
	templateUrl: './home.component.html',
	styleUrl: './home.component.css'
})
export class HomeComponent implements OnInit {

	postResponse: PostResponse[] = [];

	constructor(private postService: PostService) {}

	ngOnInit(): void {
		this.postService.getAllPosts().subscribe({
			next: (response) => {
				this.postResponse = response;
				console.log(response);
			},
			error: (error) => {
				console.log(error);
			}
		});
	}

}
