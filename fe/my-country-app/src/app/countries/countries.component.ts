import { Component, ViewChild, AfterViewInit} from '@angular/core';
import { MatPaginator, MatPaginatorModule } from '@angular/material/paginator';
import { MatSort, MatSortModule, } from '@angular/material/sort';
import { merge, of as observableOf } from 'rxjs';
import { catchError, map, startWith, switchMap } from 'rxjs/operators';
import { MatTableModule } from '@angular/material/table';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import CountriesService, { CountryDTO } from '../services/countries.service';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-countries',
  styleUrl: 'countries.component.css',
  templateUrl: 'countries.component.html',
  imports: [MatProgressSpinnerModule, MatTableModule, MatSortModule, MatPaginatorModule, RouterLink],
})
export class CountriesComponent implements AfterViewInit {


  displayedColumns: string[] = ['name', 'area', 'country_code2'];
  constructor(private countriesService: CountriesService) { }

  data: CountryDTO[] = [];

  resultsLength = 0;
  isLoadingResults = true;

  @ViewChild(MatPaginator)
  paginator!: MatPaginator;
  @ViewChild(MatSort)
  sort!: MatSort;

  ngAfterViewInit() {
    this.sort.sortChange.subscribe(() => (this.paginator.pageIndex = 0));

    merge(this.sort.sortChange, this.paginator.page)
      .pipe(
        startWith({}),
        switchMap(() => {
          this.isLoadingResults = true;
          return this.countriesService.getCountries(
            this.paginator.pageIndex,
            this.paginator.pageSize,
            this.sort
          ).pipe(catchError(() => observableOf(null)));
        }),
        map(data => {
          this.isLoadingResults = false;
          if (data === null) {
            return [];
          }

          this.resultsLength = data.totalElements;
          return data.content;
        }),
      )
      .subscribe(data => (this.data = data));
  }
}
