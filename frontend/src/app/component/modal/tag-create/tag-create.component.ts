import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { MdbModalRef } from 'mdb-angular-ui-kit/modal';
import { TagResponse } from '../../../models/tag-response';
import { TagRequest } from '../../../models/tag-request';

@Component({
  selector: 'app-tag-create',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './tag-create.component.html',
  styleUrl: './tag-create.component.css'
})
export class TagCreateComponent implements OnInit {

    tag: Partial<TagRequest> | null =  null;
    create : boolean = true;
    onSubmit(): void {
        this.close();
    }
    constructor(public modalRef: MdbModalRef<TagCreateComponent>){

    }
    close(): void {
        if (this.isTagEmpty(this.tag)) {
            return;
        }
        this.modalRef.close(this.tag);
    }

    isTagEmpty(tag: Partial<TagRequest> | null): boolean {
        return !tag || !tag.name || !tag.description;
    }

    ngOnInit(): void {
        if(this.create){
            this.tag = {
                name: '',
                description: ''
            }
        }
    }
}
