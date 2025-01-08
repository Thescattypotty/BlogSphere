import { Component } from '@angular/core';
import { AlertService } from '../../../services/alert.service';
import { NgFor } from '@angular/common';

@Component({
  selector: 'app-alert',
  standalone: true,
  imports: [NgFor],
  templateUrl: './alert.component.html',
  styleUrl: './alert.component.css'
})
export class AlertComponent {
    constructor(
        public alertService: AlertService
    ){
        
    }
    remove(message: String){
        this.alertService.messages = this.alertService.messages.filter(m => m !== message);
    }
}
