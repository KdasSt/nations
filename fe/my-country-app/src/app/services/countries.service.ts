import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Sort } from '@angular/material/sort';
import { Observable } from 'rxjs';

export interface CountryDTO {
  name: string;
  area: number;
  countryCode2: string;
}

export interface PagedResponse<T> {
  content: T[];
  page: number;
  size: number;
  totalElements: number;
  totalPages: number;
}

export interface CountryStatsDTO {
  name: string;
  countryCode3: string;
  year: number;
  population: number;
  gdp: number;

}

export interface CountryStatsJoinViewDTO {
  continentName: string;
  regionName: string;
  countryName: string;
  year: number;
  population: number;
  gdp: number;
}


@Injectable({
  providedIn: 'root'
})
export default class CountriesService {
  private apiUrl = 'http://localhost:8080/countries';

  constructor(private http: HttpClient) { }

  getCountries(page: number, size: number, sort: Sort): Observable<PagedResponse<CountryDTO>> {
    let params = new HttpParams()
      .set('page', page)
      .set('size', size)
      .set('sort', sort.active + ',' + sort.direction);

    return this.http.get<PagedResponse<CountryDTO>>(this.apiUrl, { params });
  }

  getCountryById(id: string): Observable<CountryDTO> {
    return this.http.get<CountryDTO>(`${this.apiUrl}/${id}`);
  }

  findCountriesMaxGdpPerCapita(page: number, size: number, sort: Sort): Observable<PagedResponse<CountryStatsDTO>> {
    let params = new HttpParams()
      .set('page', page)
      .set('size', size)
      .set('sort', sort.active + ',' + sort.direction);

    return this.http.get<PagedResponse<CountryStatsDTO>>(`${this.apiUrl}/stats`, { params });
  }

  getRegionStats(id: string, dateFrom: string, dateTo: string, page: number, size: number, sort: Sort): Observable<PagedResponse<CountryStatsJoinViewDTO>> {
    let params = new HttpParams()
      .set('regionId', id)
      .set('dateFrom', dateFrom)
      .set('dateTo', dateTo)
      .set('page', page)
      .set('size', size)
      .set('sort', sort.active + ',' + sort.direction);

    return this.http.get<PagedResponse<CountryStatsJoinViewDTO>>(`${this.apiUrl}/region-stats`, { params });
  }

}
