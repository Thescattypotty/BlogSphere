import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { RegisterRequest } from '../models/register-request';
import { Observable } from 'rxjs';
import { UserResponse } from '../models/user-response';
import { ERole } from '../models/role';
import { ChangepasswordRequest } from '../models/changepassword-request';
import { UserRequest } from '../models/user-request';

@Injectable({
	providedIn: 'root'
})
export class UserService {
	private API_URL = "http://localhost:8080/api/v1/users";

	constructor(private http: HttpClient) { }

	createUser(registerRequest: RegisterRequest): Observable<void> {
		return this.http.post<void>(this.API_URL, registerRequest);
	}

	getUser(id: String): Observable<UserResponse> {
		return this.http.get<UserResponse>(`${this.API_URL}/${id}`);
	}
    getUserByUsername(username: String): Observable<UserResponse> {
        return this.http.get<UserResponse>(`${this.API_URL}/username/${username}`);
    }

	getAllUsers(): Observable<UserResponse[]> {
		return this.http.get<UserResponse[]>(this.API_URL);
	}
	
    updateUser(id: String, userRequest: UserRequest): Observable<void> {
		return this.http.put<void>(`${this.API_URL}/${id}`, userRequest);
	}

    deleteUser(id: String): Observable<void> {
		return this.http.delete<void>(`${this.API_URL}/${id}`);
	}

    addRoleToUser(id: String, role: ERole): Observable<void> {
		return this.http.put<void>(`${this.API_URL}/role/${id}`, role);
	}

    removeRoleFromUser(id: String, role: ERole): Observable<void> {
		return this.http.delete<void>(`${this.API_URL}/role/${id}`, { body: role });
	}

    changePassword(id: String, changePasswordRequest: ChangepasswordRequest): Observable<void> {
		return this.http.put<void>(`${this.API_URL}/changepassword/${id}`, changePasswordRequest);
	}
}
