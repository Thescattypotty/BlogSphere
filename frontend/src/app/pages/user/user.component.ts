import { Component, OnInit } from '@angular/core';
import { UserService } from '../../services/user.service';
import { PostService } from '../../services/post.service';
import { UserResponse } from '../../models/user-response';
import { NgFor } from '@angular/common';
import { RouterLink } from '@angular/router';

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
        private userService: UserService
    ){
    }

    getUsers(): void {
        this.userService.getAllUsers().subscribe({
            next: (response) => {
                this.users = response;
            },
            error: (error) => {
                console.error(error);
            }
        })
    }

    ngOnInit(): void {
        this.getUsers();
    }


}
