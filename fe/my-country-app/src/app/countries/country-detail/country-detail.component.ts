
import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { LanguagesService, SpokenLanguageDTO } from '../../services/languages.service';
import CountriesService, { CountryDTO } from '../../services/countries.service';
import { MatListModule } from '@angular/material/list';

@Component({
  selector: 'app-country-detail',
  standalone: true,
  imports: [CommonModule, MatListModule],
  templateUrl: './country-detail.component.html',
})
export class CountryDetailComponent  implements OnInit{
   id: string | null = '';
   spokenLanguages: SpokenLanguageDTO[] = [];
   country!: CountryDTO;
  constructor(private languagesService: LanguagesService,private route: ActivatedRoute,private countriesService: CountriesService) {
    this.route.queryParams.subscribe(params => {this.id = params["id"]});
  }
  ngOnInit() {
    this.findCountryById();
    this.loadLanguages();
  }

  loadLanguages() {
    this.languagesService.getCountries(Number(this.id)).subscribe((data: SpokenLanguageDTO[]) => {
      this.spokenLanguages = data;
    });
  }

  findCountryById() {
    if (this.id){
      this.countriesService.getCountryById(this.id).subscribe((data: CountryDTO) => {
      this.country = data;
    });
    }
    
  }

}
