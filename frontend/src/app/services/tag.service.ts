import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { TagRequest } from '../models/tag-request';
import { Observable } from 'rxjs';
import { TagResponse } from '../models/tag-response';

@Injectable({
	providedIn: 'root'
})
export class TagService {
	private API_URL = 'http://localhost:8080/api/v1/tags';

	constructor(private http: HttpClient) { }

	createTag(tagRequest: TagRequest): Observable<void> {
		return this.http.post<void>(this.API_URL, tagRequest);
	}

	getTag(id: String): Observable<TagResponse> {
		return this.http.get<TagResponse>(`${this.API_URL}/${id}`);
	}

	getAllTags(): Observable<TagResponse[]> {
		return this.http.get<TagResponse[]>(this.API_URL);
	}

    updateTag(id: String, tagRequest: TagRequest): Observable<void> {
		return this.http.put<void>(`${this.API_URL}/${id}`, tagRequest);
	}
    
    getTagsByUser(username: String): Observable<TagResponse[]> {
        return this.http.get<TagResponse[]>(`${this.API_URL}/user/${username}`);
    }

    deleteTag(id: String): Observable<void> {
		return this.http.delete<void>(`${this.API_URL}/${id}`);
	}
    tagsNameByIds(ids: String[]): Observable<String[]> {
        return this.http.post<String[]>(`${this.API_URL}/names`, ids);
    }
}
