import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { LoginRequest } from '../models/login-request';
import { Observable } from 'rxjs';
import { JwtResponse } from '../models/jwt-response';
import { RegisterRequest } from '../models/register-request';

@Injectable({
	providedIn: 'root'
})
export class AuthService {
	private API_URL = 'http://localhost:8080/api/v1/auth';

	constructor(private http: HttpClient) { }

	login(loginRequest: LoginRequest): Observable<JwtResponse> {
		return this.http.post<JwtResponse>(`${this.API_URL}/login`, loginRequest);
	}

	register(registerRequest: RegisterRequest): Observable<void> {
		return this.http.post<void>(`${this.API_URL}/register`, registerRequest);
	}
}
