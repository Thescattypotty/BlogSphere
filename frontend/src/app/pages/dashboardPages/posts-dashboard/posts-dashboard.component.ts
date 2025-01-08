import { Component, OnInit } from '@angular/core';
import { PostService } from '../../../services/post.service';
import { PostResponse } from '../../../models/post-response';
import { MdbModalModule, MdbModalRef, MdbModalService } from 'mdb-angular-ui-kit/modal';
import { PostFormComponent } from '../../../component/modal/post-form/post-form.component';
import { NgFor, NgIf } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { PostRequest } from '../../../models/post-request';
import { TagService } from '../../../services/tag.service';
import { TagResponse } from '../../../models/tag-response';
import { AlertService } from '../../../services/alert.service';

@Component({
    selector: 'app-posts-dashboard',
    standalone: true,
    imports: [NgFor, FormsModule, MdbModalModule, NgIf],
    templateUrl: './posts-dashboard.component.html',
    styleUrl: './posts-dashboard.component.css'
})
export class PostsDashboardComponent implements OnInit {

    posts: PostResponse[] = [];
    isLoading: boolean = true;
    postGetted: PostResponse | null = null;
    tags: TagResponse[] = [];

    modalRef: MdbModalRef<PostFormComponent> | null = null;

    constructor(
        private postService: PostService,
        private modalService: MdbModalService,
        private tagService: TagService,
        private alertService: AlertService
    ) {
    }

    addPost() {
        this.modalRef = this.modalService.open(PostFormComponent,{
            modalClass: 'modal-lg'
        });
        this.modalRef.onClose.subscribe((newPost: PostRequest) => {
            if(newPost === undefined){
                return;
            }
            this.postService.createPost(newPost).subscribe({
                next: (response) => {
                    this.reloadPosts();
                },
                error: (error) => {
                    this.alertService.add('Error Creating Post : '+ error);
                }
            });
        });
    }
    
    editPost(id: String) {
        this.postService.getPost(id).subscribe({
            next: (response) => {
                this.postGetted = response;
                this.modalRef = this.modalService.open(PostFormComponent, {
                    modalClass: 'modal-lg',
                    data: {
                        create: false,
                        post: {
                            'title': this.postGetted?.title,
                            'content': this.postGetted?.content,
                            'coverImage': this.postGetted?.coverImage,
                            'isPublished': this.postGetted?.isPublished,
                            'tagsId': this.postGetted?.tagsId,
                            'commentsId': this.postGetted?.commentsId,
                        }
                    }
                });
                this.modalRef.onClose.subscribe((editedPost: PostRequest) => {
                    if(editedPost === undefined){
                        return;
                    }
                    this.postService.updatePost(id, editedPost).subscribe({
                        next: (response) => {
                            this.reloadPosts();
                        },
                        error: (error) => {
                            this.alertService.add('Error Updating Post : '+ error);
                        }
                    });
                });
            },
            error: (error) => {
                this.alertService.add('Error Getting Post : '+ error);
            }
        });
    }
    deletePost(id: String) {
        this.postService.deletePost(id).subscribe({
            next: (response) => {
                this.reloadPosts();
            },
            error: (error) => {
                this.alertService.add('Error Deleting Post : '+ error);
            }
        });
    }
    reloadPosts() {
        this.postService.getAllPosts().subscribe({
            next: (response) => {
                this.posts = response;
                this.isLoading = false;
            },
            error: (error) => {
                this.alertService.add('Error Getting Posts : '+ error);
            }
        });
    }

    reloadTags(){
        this.tagService.getAllTags().subscribe({
            next: (response) => {
                this.tags = response;
            },
            error: (error) =>{
                this.alertService.add(error);
            }
        });
    }

    getTagsNames(tagsId: String[]) {
        let tagNames: String = '';

        if (!tagsId || tagsId.length === 0) {
            return tagNames;
        }

        tagsId.forEach((tagId, index) => {
            const tag = this.tags.find(t => t.id === tagId);
            if (tag) {
                tagNames += tag.name.toString();
                if (index < tagsId.length - 1) {
                    tagNames += ', ';
                }
            }
        });

        return tagNames;
    }

    ngOnInit(): void {
        this.reloadPosts();
        this.reloadTags();
    }

}
