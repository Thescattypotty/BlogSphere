import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { MdbModalRef } from 'mdb-angular-ui-kit/modal';
import { PostRequest } from '../../../models/post-request';
import { TagResponse } from '../../../models/tag-response';
import { TagService } from '../../../services/tag.service';
import { CommonModule, NgFor } from '@angular/common';
import { PostDesctiptionEditorComponent } from "../../post-desctiption-editor/post-desctiption-editor.component";
import { AlertService } from '../../../services/alert.service';

@Component({
    selector: 'app-post-form',
    standalone: true,
    imports: [FormsModule, NgFor, CommonModule, PostDesctiptionEditorComponent],
    templateUrl: './post-form.component.html',
    styleUrl: './post-form.component.css'
})
export class PostFormComponent implements OnInit {

    post: Partial<PostRequest> | null = null;
    create: boolean = true;
    tags: TagResponse[] = [];
    
    onSubmit(): void {
        this.close();
    }
    constructor(
        public modalRef: MdbModalRef<PostFormComponent>,
        private tagService: TagService,
        private alertService: AlertService
    ) {

    }

    close(): void {
        if (this.isPostEmpty(this.post)) {
            this.alertService.add("Post is empty");
            return;
        }
        this.modalRef.close(this.post);
    }
    isPostEmpty(post: Partial<PostRequest> | null): boolean {
        return !post || !post.title || !post.content || !post.isPublished || !post.tagsId;
    }

    getTags(): void{
        this.tagService.getAllTags().subscribe({
            next: (response) => {
                this.tags = response;
            },
            error: (error) => {
                this.alertService.add('Error Getting Tags : '+ error);
                this.modalRef.close();
            }
        });
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
        this.getTags();
    }
}
