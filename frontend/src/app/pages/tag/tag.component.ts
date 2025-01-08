import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { TagService } from '../../services/tag.service';
import { PostService } from '../../services/post.service';
import { PostResponse } from '../../models/post-response';
import { NgFor } from '@angular/common';
import { CardPostComponent } from "../../component/card-post/card-post.component";
import { TagResponse } from '../../models/tag-response';
import { AlertService } from '../../services/alert.service';

@Component({
  selector: 'app-tag',
  standalone: true,
  imports: [NgFor, CardPostComponent],
  templateUrl: './tag.component.html',
  styleUrl: './tag.component.css'
})
export class TagComponent implements OnInit {
    id: String = '';
    tag: TagResponse | null = null;
    posts: PostResponse[] = [];
    tagsName: String[] = [];
    constructor(
        private route: ActivatedRoute,
        private tagService: TagService,
        private postService: PostService,
        private router: Router,
        private alertService: AlertService
    ) {

    }

    ngOnInit() {
        this.route.paramMap.subscribe(params => {
            const idParam = params.get('id');
            if (idParam) {
                this.id = idParam;
                this.getTag();
                this.getPosts();
            } else {
                this.router.navigate(['/']);
            }
        });
    }

    getTag(){
        this.tagService.getTag(this.id).subscribe({
            next: (response) => {
                this.tag = response;
            },
            error: (error) => {
                this.alertService.add(error);
            }
        });
    }

    getPosts(){
        this.postService.getPostsByTag(this.id).subscribe({
            next: (response) => {
                this.posts = response;
            },
            error: (error) => {
                this.alertService.add(error);
            }
        });
    }
}

