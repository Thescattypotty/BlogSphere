import { Component, OnInit } from '@angular/core';
import { UserService } from '../../services/user.service';
import { PostService } from '../../services/post.service';
import { UserResponse } from '../../models/user-response';
import { NgFor } from '@angular/common';
import { RouterLink } from '@angular/router';
import { AlertService } from '../../services/alert.service';

@Component({
  selector: 'app-user',
  standalone: true,
  imports: [NgFor, RouterLink],
  templateUrl: './user.component.html',
  styleUrl: './user.component.css'
})
export class UserComponent implements OnInit{

    users: UserResponse[] = [];

    constructor(
        private userService: UserService,
        private alertService: AlertService
    ){
    }

    getUsers(): void {
        this.userService.getAllUsers().subscribe({
            next: (response) => {
                this.users = response;
            },
            error: (error) => {
                this.alertService.add(error);
            }
        })
    }

    ngOnInit(): void {
        this.getUsers();
    }


}
