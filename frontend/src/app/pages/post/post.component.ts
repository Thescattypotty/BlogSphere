import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { PostService } from '../../services/post.service';
import { TagService } from '../../services/tag.service';
import { PostResponse } from '../../models/post-response';
import { PostSidebarComponent } from "../../component/post-sidebar/post-sidebar.component";
@Component({
    selector: 'app-post',
    standalone: true,
    imports: [PostSidebarComponent, RouterLink],
    templateUrl: './post.component.html',
    styleUrl: './post.component.css'
})
export class PostComponent implements OnInit {

    id: String = '';
    post: PostResponse | null = null;
    constructor(
        private route: ActivatedRoute,
        private router: Router,
        private postService: PostService,
        private tagService: TagService,
    ) { }

    ngOnInit(): void {
        this.route.paramMap.subscribe(params => {
            const idParam = params.get('id');
            if(idParam){
                this.id = idParam;
                this.getPost();
            }else{
                this.router.navigate(['/']);
            }
        });
    }
    getPost() {
        this.postService.getPost(this.id).subscribe({
            next: (response) => {
                this.post = response;
                console.log(response);
            },
            error: (error) => {
                console.log(error);
            }
        });
    }

}
