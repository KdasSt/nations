import { Component, ViewChild, AfterViewInit } from '@angular/core';
import { MatPaginator, MatPaginatorModule } from '@angular/material/paginator';
import { MatSort, MatSortModule, } from '@angular/material/sort';
import { merge, of as observableOf } from 'rxjs';
import { catchError, map, startWith, switchMap } from 'rxjs/operators';
import { MatTableModule } from '@angular/material/table';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import CountriesService, { CountryStatsDTO, CountryStatsJoinViewDTO } from '../../services/countries.service';
import { RouterLink } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { RegionDTO, RegionService } from '../../services/region.service';


@Component({
  selector: 'app-region-stats',
  imports: [CommonModule, ReactiveFormsModule, ReactiveFormsModule,
    MatFormFieldModule, MatInputModule, MatSelectModule,
    MatProgressSpinnerModule, MatTableModule, MatPaginatorModule, MatSortModule],
  templateUrl: './region-stats.component.html',
  styleUrl: './region-stats.component.css'
})
export class RegionStatsComponent implements AfterViewInit{
  form: FormGroup;

  regions: RegionDTO[] = [];
  data: CountryStatsJoinViewDTO[] = [];
  displayedColumns: string[] = ['continent_name', 'region_name', 'country_name', 'year', 'population', 'gdp'];
  regionId: string = '1';
  dateFrom: string = '1';
  dateTo: string = '1';

  resultsLength = 0;
  isLoadingResults = false;

  @ViewChild(MatPaginator)
  paginator!: MatPaginator;
  @ViewChild(MatSort)
  sort!: MatSort;

  constructor(private fb: FormBuilder, private countriesService: CountriesService, private regionService: RegionService) {
    this.form = this.fb.group({
      dropdown: ['', Validators.required],
      inputOne: ['', Validators.required],
      inputTwo: ['', Validators.required]
    });
  }

  onSubmit() {
    if (this.form.valid) {
      this.regionId = this.form.value.dropdown;
      this.dateFrom = this.form.value.inputOne;
      this.dateTo = this.form.value.inputTwo;
      this.paginator.pageIndex = 0;
      this.fetchData();

    } else {
      console.log('Form invalid');
      this.form.markAllAsTouched();
    }

  }

  ngOnInit() {
    this.loadRegions();
    // console.log(this.regions);
  }

  loadRegions() {
    this.regionService.getRegions().subscribe((data: RegionDTO[]) => {
      this.regions = data
    });
  }

  ngAfterViewInit() {

    this.sort.sortChange.subscribe(() => (this.paginator.pageIndex = 0));

    merge(this.sort.sortChange, this.paginator.page)
      .pipe(
        startWith({}),
        switchMap(() => {
          this.isLoadingResults = true;
          return this.countriesService.getRegionStats(
            this.regionId,
            this.dateFrom,
            this.dateTo,
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
          console.log("Hello", data.content);
          return data.content;
        }),
      )
      .subscribe(data => (this.data = data));
  }

  fetchData() {
    // Reset to first page when paginator changes
    this.paginator.pageIndex = 0;

    this.paginator.page
      .pipe(
        startWith({}),
        switchMap(() => {
          this.isLoadingResults = true;
          return this.countriesService.getRegionStats(
            this.regionId,
            this.dateFrom,
            this.dateTo,
            this.paginator.pageIndex,
            this.paginator.pageSize,
            this.sort
          ).pipe(
            catchError(() => observableOf(null))
          );
        }),
        map(data => {
          this.isLoadingResults = false;
          if (data === null) {
            return [];
          }
          this.resultsLength = data.totalElements;
          console.log('Hello', data.content);
          return data.content;
        })
      )
      .subscribe(data => (this.data = data));
  }


}
