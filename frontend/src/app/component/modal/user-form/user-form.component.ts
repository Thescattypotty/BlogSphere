import { Component, OnInit } from '@angular/core';
import { UserRequest } from '../../../models/user-request';
import { RegisterRequest } from '../../../models/register-request';
import { MdbModalRef } from 'mdb-angular-ui-kit/modal';
import { NgIf } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-user-form',
  standalone: true,
  imports: [NgIf, FormsModule],
  templateUrl: './user-form.component.html',
  styleUrl: './user-form.component.css'
})
export class UserFormComponent implements OnInit{

    user: Partial<RegisterRequest> | null = null;
    create: boolean = true;
    
    onSubmit(): void {
        this.close();
    }
    constructor(
        public modalRef: MdbModalRef<UserFormComponent>,
    ) {

    }
    close(): void {
        if (this.isUserEmpty(this.user)) {
            console.log("User is empty");
            return;
        }
        this.modalRef.close(this.user);
    }

    isUserEmpty(user: Partial<RegisterRequest> | null): boolean {
        return user === null || user.username === '' || user.email === '' || user.password === '' || user.firstName === '' || user.lastName === '' || user.profilePicture === '' || user.bio === '';
    }

    ngOnInit(): void {
        if(this.create){
            this.user = {
                username: '',
                email: '',
                password: '',
                firstName: '',
                lastName: '',
                roles: [],
                profilePicture: '',
                bio: ''
            };
        }
    }

}