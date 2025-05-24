import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/internal/Observable';

export interface SpokenLanguageDTO {
  language: string;
  isOfficial: boolean;
}

@Injectable({
  providedIn: 'root'
})
export class LanguagesService {
  private apiUrl = 'http://localhost:8080/languages';

  constructor(private http: HttpClient) {}

  getCountries(id: number): Observable<SpokenLanguageDTO[]> {
    let params = new HttpParams()
      .set('id', id);

    return this.http.get<SpokenLanguageDTO[]>(this.apiUrl, { params });
  }
}
