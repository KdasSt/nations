import { Component, ViewChild, AfterViewInit} from '@angular/core';
import { MatPaginator, MatPaginatorModule } from '@angular/material/paginator';
import { MatSort, MatSortModule, } from '@angular/material/sort';
import { merge, of as observableOf } from 'rxjs';
import { catchError, map, startWith, switchMap } from 'rxjs/operators';
import { MatTableModule } from '@angular/material/table';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import CountriesService, { CountryStatsDTO } from '../../services/countries.service';
import { RouterLink } from '@angular/router';


@Component({
  selector: 'app-country-stats',
  imports: [MatProgressSpinnerModule, MatTableModule, MatSortModule, MatPaginatorModule],
  templateUrl: './country-stats.component.html',
  styleUrl: './country-stats.component.css'
})
export class CountryStatsComponent implements AfterViewInit {


  displayedColumns: string[] = ['name', 'country_code3', 'year', 'population', 'gdp'];
  constructor(private countriesService: CountriesService) { }

  data: CountryStatsDTO[] = [];

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
          return this.countriesService.findCountriesMaxGdpPerCapita(
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
