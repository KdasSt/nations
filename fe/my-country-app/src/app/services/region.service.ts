import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/internal/Observable';

export interface RegionDTO {
  name: string;
  id: number;
}

@Injectable({
  providedIn: 'root'
})
export class RegionService {

  private apiUrl = 'http://localhost:8080/regions';

  constructor(private http: HttpClient) { }

  getRegions(): Observable<RegionDTO[]> {
      return this.http.get<RegionDTO[]>(`${this.apiUrl}`);
    }

}
