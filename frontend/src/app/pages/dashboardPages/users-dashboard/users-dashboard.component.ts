import { NgFor } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { MdbModalModule, MdbModalRef, MdbModalService } from 'mdb-angular-ui-kit/modal';
import { UserResponse } from '../../../models/user-response';
import { UserFormComponent } from '../../../component/modal/user-form/user-form.component';
import { UserService } from '../../../services/user.service';
import { UserRequest } from '../../../models/user-request';
import { RegisterRequest } from '../../../models/register-request';
import { create } from 'domain';

@Component({
  selector: 'app-users-dashboard',
  standalone: true,
  imports: [NgFor, FormsModule, MdbModalModule],
  templateUrl: './users-dashboard.component.html',
  styleUrl: './users-dashboard.component.css'
})
export class UsersDashboardComponent implements OnInit{

    users: UserResponse[] = [];
    isLoading: boolean = true;
    userGetted: UserResponse | null = null;

    modalRef: MdbModalRef<UserFormComponent> | null = null;

    constructor(
        private userService: UserService,
        private modalService: MdbModalService
    ){

    }

    addUser(){
        this.modalRef = this.modalService.open(UserFormComponent, {
            modalClass: 'modal-lg'
        });
        this.modalRef.onClose.subscribe((newUser: RegisterRequest) => {
            if(newUser === undefined){
                return;
            }
            this.userService.createUser(newUser).subscribe({
                next: (response) => {
                    console.log('Response : ', response);
                    this.reloadUsers();
                },
                error: (error) => {
                    console.error('Error Creating User : ', error);
                }
            });
        });
    }

    editUser(id: String){
        this.userService.getUser(id).subscribe({
            next: (response) => {
                this.userGetted = response;
                this.modalRef = this.modalService.open(UserFormComponent, {
                    modalClass: 'modal-lg',
                    data: {
                        create: false,
                        user: {
                            username: this.userGetted.username,
                            email: this.userGetted.email,
                            password: '',
                            firstName: this.userGetted.firstName,
                            lastName: this.userGetted.lastName,
                            roles: this.userGetted.roles,
                            profilePicture: this.userGetted.profilePicture,
                            bio: this.userGetted.bio
                        }
                    }
                });
                this.modalRef.onClose.subscribe((editedUser: UserRequest) => {
                    if(editedUser === undefined){
                        return;
                    }
                    this.userService.updateUser(id, editedUser).subscribe({
                        next: (response) => {
                            console.log('Response : ', response);
                            this.reloadUsers();
                        },
                        error: (error) => {
                            console.error('Error Editing User : ', error);
                        }
                    });
                });
            },
            error: (error) => {
                console.error('Error Getting User : ', error);
            }
        });
    }
    deleteUser(id: String){
        this.userService.deleteUser(id).subscribe({
            next: (response) => {
                console.log('Response : ', response);
                this.reloadUsers();
            },
            error: (error) => {
                console.error('Error Deleting User : ', error);
            }
        });
    }

    reloadUsers(){
        this.isLoading = true;
        this.userService.getAllUsers().subscribe({
            next: (response) => {
                this.users = response;
                this.isLoading = false;
            },
            error: (error) => {
                console.error('Error Getting Users : ', error);
                this.isLoading = false;
            }
        });
    }

    ngOnInit(): void {
        this.reloadUsers();
    }

}
