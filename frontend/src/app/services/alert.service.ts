import { Injectable } from '@angular/core';

@Injectable({
    providedIn: 'root'
})
export class AlertService {
    constructor() { }
    messages : String[] = [];
    add(message: string) {
        this.messages.push(message);
    }
    clear() {
        this.messages = [];
    }
}
