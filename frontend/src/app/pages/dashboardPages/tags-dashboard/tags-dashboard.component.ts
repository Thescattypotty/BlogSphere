import { Component, NgModule, OnInit, TemplateRef } from '@angular/core';
import { Router } from '@angular/router';
import { TagService } from '../../../services/tag.service';
import { TagResponse } from '../../../models/tag-response';
import { catchError, of } from 'rxjs';
import { NgFor } from '@angular/common';
import { MdbModalModule, MdbModalRef, MdbModalService } from 'mdb-angular-ui-kit/modal';
import { FormsModule } from '@angular/forms';
import { TagCreateComponent } from '../../../component/modal/tag-create/tag-create.component';
import { TagRequest } from '../../../models/tag-request';

@Component({
    selector: 'app-tags-dashboard',
    standalone: true,
    imports: [NgFor, FormsModule, MdbModalModule],
    templateUrl: './tags-dashboard.component.html',
    styleUrl: './tags-dashboard.component.css'
})
export class TagsDashboardComponent implements OnInit{

    tags: TagResponse[] = [];
    isLoading: boolean = true;
    tagGetted: TagResponse | null = null;
    
    modalRef: MdbModalRef<TagCreateComponent> | null = null;

    constructor(
        private router: Router,
        private tagService: TagService,
        private modalService: MdbModalService
    ){}


    addTag() {
        this.modalRef = this.modalService.open(TagCreateComponent);
        this.modalRef.onClose.subscribe((newTag : TagRequest) => {
            if(newTag === undefined){
                return;
            }
            this.tagService.createTag(newTag).subscribe({
                next: (response) => {
                    console.log('Response : ' , response);
                    this.reloadTags();
                },
                error: (error) => {
                    console.error('Error Creating Tag : ', error);
                }
            })
        })
    }
    
    editTag(id: String){
        this.tagService.getTag(id).subscribe({
            next: (response) => {
                this.tagGetted = response;
                this.modalRef = this.modalService.open(TagCreateComponent, {
                    data: {
                        create: false,
                        tag: {
                            'name': this.tagGetted?.name,
                            'description': this.tagGetted?.description
                        }
                    }
                });

                this.modalRef.onClose.subscribe((editTag: TagRequest) => {
                    if(editTag === undefined){
                        return;
                    }
                    this.tagService.updateTag(id, editTag).subscribe({
                        next: (response) => {
                            console.log('Response : ' , response);
                            this.reloadTags();
                        },
                        error: (error) => {
                            console.error('Error Updating Tag : ', error);
                        }
                    });
                });
            },
            error: (error) => {
                console.error('Error Fetching Tag : ', error);
            }
        });
    }

    deleteTag(id: String){
        this.tagService.deleteTag(id).subscribe({
            next: (response) => {
                console.log('Response : ' , response);
                this.reloadTags();
            },
            error: (error) => {
                console.error('Error Deleting Tag : ', error);
            }
        });
    }

    reloadTags(): void{
        this.tagService.getAllTags().pipe(
            catchError(error => {
                console.error('Error fetching tags:', error);
                return of([]);
            })
        ).subscribe({
            next: (response) => {
                this.tags = response;
                this.isLoading = false;
            }
        });
    }

    ngOnInit(): void {
        this.reloadTags();
    }
}
