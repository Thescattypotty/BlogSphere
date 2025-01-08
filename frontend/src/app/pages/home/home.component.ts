import { Component, OnInit } from '@angular/core';
import { CardPostComponent } from "../../component/card-post/card-post.component";
import { PostService } from '../../services/post.service';
import { PostResponse } from '../../models/post-response';
import { NgFor } from '@angular/common';
import { AlertService } from '../../services/alert.service';

@Component({
	selector: 'app-home',
	standalone: true,
	imports: [CardPostComponent, NgFor],
	templateUrl: './home.component.html',
	styleUrl: './home.component.css'
})
export class HomeComponent implements OnInit {

	posts: PostResponse[] = [];

	constructor(
        private postService: PostService,
        private alertService: AlertService
    ) {}

    loadPosts(){
        this.postService.getAllPosts().subscribe({
            next: (response) => {
                this.posts = response;
            },
            error: (error) => {
                this.alertService.add(error);
            }
        });
    }

	ngOnInit(): void {
		this.loadPosts();
	}

}
