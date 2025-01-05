import { NgFor, NgIf } from '@angular/common';
import { Component, Input, OnInit } from '@angular/core';
import { DomSanitizer, SafeUrl } from '@angular/platform-browser';
import { TagService } from '../../services/tag.service';

@Component({
	selector: 'app-card-post',
	standalone: true,
	imports: [NgIf],
	templateUrl: './card-post.component.html',
	styleUrl: './card-post.component.css'
})
export class CardPostComponent implements OnInit {
	@Input() listTags: String[] = [];
	@Input() title: String = '';
	@Input() description: String = '';
	@Input() link: String = '';
	@Input() textLink: String = '';
    
    @Input() set coverImage(value: String) {
        this._coverImage = this.sanitizer.bypassSecurityTrustUrl(value.toString());
    }
    
    get coverImage(): SafeUrl {
        return this._coverImage;
    }

    private _coverImage: SafeUrl;
    tagNames: String[] = [];

    constructor(
        private sanitizer: DomSanitizer,
        private tagService: TagService
    ) {
        this._coverImage = '';
    }


    ngOnInit(){
        this.setTagName();
    }

    setTagName(){
        this.listTags.forEach(tagId => {
            this.tagService.getTag(tagId).subscribe({
                next: (response) => {
                    this.tagNames.push(response.name);
                },
                error: (error) => {
                    console.log(error);
                }
            })
        })
    }

}
