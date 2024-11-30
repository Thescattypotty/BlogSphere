import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { PostRequest } from '../models/post-request';
import { Observable } from 'rxjs';
import { PostResponse } from '../models/post-response';
import { CommentRequest } from '../models/comment-request';

@Injectable({
  providedIn: 'root'
})
export class PostService {
	private API_URL = 'http://localhost:8080/api/v1/posts';

	constructor(private http: HttpClient) { }

	createPost(postRequest: PostRequest): Observable<void> {
		return this.http.post<void>(this.API_URL, postRequest);
	}

	getPost(id: string): Observable<PostResponse> {
		return this.http.get<PostResponse>(`${this.API_URL}/${id}`);
	}

	getAllPosts(): Observable<PostResponse[]> {
		return this.http.get<PostResponse[]>(this.API_URL);
	}

	updatePost(id: string, postRequest: PostRequest): Observable<void> {
		return this.http.put<void>(`${this.API_URL}/${id}`, postRequest);
	}

	deletePost(id: string): Observable<void> {
		return this.http.delete<void>(`${this.API_URL}/${id}`);
	}

	createComment(postId: string, commentRequest: CommentRequest): Observable<void> {
		return this.http.post<void>(`${this.API_URL}/${postId}/comment/`, commentRequest);
	}

	addTagToPost(postId: string, tagId: string): Observable<void> {
		return this.http.put<void>(`${this.API_URL}/${postId}/${tagId}`, {});
	}

	removeTagFromPost(postId: string, tagId: string): Observable<void> {
		return this.http.delete<void>(`${this.API_URL}/${postId}/${tagId}`);
	}
}
