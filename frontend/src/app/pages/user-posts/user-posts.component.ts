import { Component, OnInit } from '@angular/core';
import { PostResponse } from '../../models/post-response';
import { UserService } from '../../services/user.service';
import { PostService } from '../../services/post.service';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { UserResponse } from '../../models/user-response';
import { NgFor } from '@angular/common';
import { CardPostComponent } from '../../component/card-post/card-post.component';

@Component({
    selector: 'app-user-posts',
    standalone: true,
    imports: [NgFor, RouterLink, CardPostComponent],
    templateUrl: './user-posts.component.html',
    styleUrl: './user-posts.component.css'
})
export class UserPostsComponent implements OnInit {

    username : String = '';
    posts: PostResponse[] = [];
    user: UserResponse | null = null;

    constructor(
        private userService: UserService,
        private postService: PostService,
        private route: ActivatedRoute,
        private router: Router,
    ) { }

    ngOnInit(): void {
        this.route.paramMap.subscribe(params => {
            const usernameParam = params.get('username');
            if(usernameParam){
                this.username = usernameParam;
                this.getUser();
                this.getPosts();
            }else{
                this.router.navigate(['/']);
            }
        });
    }

    getUser(){
        this.userService.getUserByUsername(this.username).subscribe({
            next: (response) => {
                this.user = response;
            },
            error: (error) => {
                console.error(error);
            }
        });
    }

    getPosts(){
        this.postService.getPostsByUser(this.username).subscribe({
            next: (response) => {
                this.posts = response;
            },
            error: (error) => {
                console.error(error);
            }
        });
    }

}
