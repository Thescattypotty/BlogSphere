import { CommonModule } from '@angular/common';
import { Component, Input, OnInit, ViewChild, ElementRef, Output, EventEmitter, OnChanges, SimpleChanges } from '@angular/core';
import { FormsModule } from '@angular/forms';

@Component({
    selector: 'app-post-desctiption-editor',
    standalone: true,
    imports: [CommonModule, FormsModule],
    templateUrl: './post-desctiption-editor.component.html',
    styleUrl: './post-desctiption-editor.component.css',
})
export class PostDesctiptionEditorComponent implements OnInit, OnChanges {
    @Input()
    public content: String | undefined = undefined;

    @Output() contentChange = new EventEmitter<string>();

    @ViewChild('editor', { static: true }) editor!: ElementRef;

    ngOnInit(): void {
    }

    ngOnChanges(changes: SimpleChanges): void {
        if (this.editor && this.content !== this.editor.nativeElement.innerHTML) {
            this.editor.nativeElement.innerHTML = this.content;
        }
    }

    format(command: string, event?: Event | null, value?: string) {
        if (event) {
            event.preventDefault();
            event.stopPropagation();
        }
        document.execCommand(command, false, value);
        this.content = this.editor.nativeElement.innerHTML;
    }

    openPromptForImage(event: Event): void {
        event.preventDefault();
        const url = window.prompt("URL de l'image :");
        if (url) {
            this.format('insertImage', null, url);
        }
    }

    openPromptForLink(event: Event): void {
        event.preventDefault();
        const url = window.prompt('URL du lien :') || '';
        if (url) {
            this.format('createLink', null, url);
        }
    }

    removeFormat(event: Event): void {
        event.preventDefault();
        this.format('removeFormat', event);
    }

    onContentInput(): void {
        const selection = window.getSelection();
        const range = selection?.getRangeAt(0);
        this.content = this.editor.nativeElement.innerHTML;
        if(this.content){
            this.contentChange.emit(this.content.toString());
        }
        if (range && selection) {
            selection.removeAllRanges();
            selection.addRange(range);
        }
    }

    onContentChange(): void {
        console.log('Contenu actuel :', this.content);
    }
}
