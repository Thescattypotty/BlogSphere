import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { MdbModalRef } from 'mdb-angular-ui-kit/modal';
import { PostRequest } from '../../../models/post-request';

@Component({
    selector: 'app-post-form',
    standalone: true,
    imports: [FormsModule],
    templateUrl: './post-form.component.html',
    styleUrl: './post-form.component.css'
})
export class PostFormComponent implements OnInit {

    post: Partial<PostRequest> | null = null;
    create: boolean = true;
    
    onSubmit(): void {
        this.close();
    }
    constructor(public modalRef: MdbModalRef<PostFormComponent>) {

    }
    close(): void {
        if (this.isPostEmpty(this.post)) {
            return;
        }
        this.modalRef.close(this.post);
    }
    isPostEmpty(post: Partial<PostRequest> | null): boolean {
        return !post || !post.title || !post.content || !post.isPublished || !post.tagsId;
    }

    ngOnInit(): void {
        if (this.create) {
            this.post = {
                title: '',
                content: '',
                isPublished: true,
                tagsId: []
            }
        }
    }
}
