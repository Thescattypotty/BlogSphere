import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { PostService } from '../../services/post.service';
import { TagService } from '../../services/tag.service';
import { PostResponse } from '../../models/post-response';
import { PostSidebarComponent } from "../../component/post-sidebar/post-sidebar.component";
import { DomSanitizer, SafeHtml } from '@angular/platform-browser';
import { DatePipe, NgIf } from '@angular/common';
import { AlertService } from '../../services/alert.service';
@Component({
    selector: 'app-post',
    standalone: true,
    imports: [PostSidebarComponent, RouterLink, NgIf],
    templateUrl: './post.component.html',
    styleUrl: './post.component.css'
})
export class PostComponent implements OnInit {

    id: String = '';
    post: PostResponse | null = null;
    safeHtml: SafeHtml | null = null;
    randomPost: PostResponse[] = [];

    constructor(
        private route: ActivatedRoute,
        private router: Router,
        private postService: PostService,
        private sanitizer: DomSanitizer,
        private alertService: AlertService
    ) { }

    ngOnInit(): void {
        this.route.paramMap.subscribe(params => {
            const idParam = params.get('id');
            if(idParam){
                this.id = idParam;
                this.getPost();
                this.get2RandomPosts();
            }else{
                this.router.navigate(['/']);
            }
        });
    }
    getPost() {
        this.postService.getPost(this.id).subscribe({
            next: (response) => {
                this.post = response;
                this.safeHtml = this.sanitizer.bypassSecurityTrustHtml(response.content.toString() || '');
            },
            error: (error) => {
                this.alertService.add(error);
            }
        });
    }
    get2RandomPosts(){
        this.postService.getAllPosts().subscribe({
            next: (response) => {
                this.randomPost = response.sort(() => Math.random() - 0.5).slice(0, 2);
            },
            error: (error) => {
                this.alertService.add('Error Getting Random Posts : '+ error);
            }
        })
    }

}
