import { Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { CountriesComponent } from './countries/countries.component';
import { CountryDetailComponent } from './countries/country-detail/country-detail.component';
import { CountryStatsComponent } from './countries/country-stats/country-stats.component';
import { RegionStatsComponent } from './countries/region-stats/region-stats.component';

export const routes: Routes = [
  { path: '', component: CountriesComponent },
  { path: 'languages', component: CountryDetailComponent },
  { path: 'country-stats', component: CountryStatsComponent },
  { path: 'region-stats', component: RegionStatsComponent },
];



