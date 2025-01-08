import { Component, OnInit } from '@angular/core';
import { PostResponse } from '../../models/post-response';
import { PostService } from '../../services/post.service';
import { NgFor } from '@angular/common';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-post-sidebar',
  standalone: true,
  imports: [NgFor, RouterLink],
  templateUrl: './post-sidebar.component.html',
  styleUrl: './post-sidebar.component.css'
})
export class PostSidebarComponent implements OnInit {

    posts: PostResponse[] = [];

    constructor(private postService: PostService) {
    }

    reloadPosts(){
        this.postService.getAllPosts().subscribe({
            next: (response) => {
                this.posts = this.getRandomPosts(response, 3, 4);
            },
            error: (error) => {
                console.error('Error Fetching Posts : ', error);
            }
        });
    }

    private getRandomPosts(posts: PostResponse[], min: number, max: number): PostResponse[] {
        const count = Math.floor(Math.random() * (max - min + 1)) + min;
        return posts.sort(() => 0.5 - Math.random()).slice(0, count);
    }

    ngOnInit(): void {
        this.reloadPosts();
    }
    
}
