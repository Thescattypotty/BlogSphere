import { NgFor } from '@angular/common';
import { Component, Input } from '@angular/core';

@Component({
	selector: 'app-card-post',
	standalone: true,
	imports: [NgFor],
	templateUrl: './card-post.component.html',
	styleUrl: './card-post.component.css'
})
export class CardPostComponent {
	@Input() listTags: String[] = [];
	@Input() title: String = '';
	@Input() description: String = '';
	@Input() link: String = '';
	@Input() textLink: String = '';
}
