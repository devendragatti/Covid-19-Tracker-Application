import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Router } from '@angular/router';
import { Ccountry } from '../ccountry';
import { ccovidservice } from '../ccovidservice.service';
import { SearchcountryComponent } from '../searchcountry/searchcountry.component';

@Component({
  selector: 'app-countrylist',
  templateUrl: './countrylist.component.html',
  styleUrls: ['./countrylist.component.css']
})
export class CountrylistComponent implements OnInit {


  @Input()
  Ccountry: Ccountry;
  constructor(private ccovidservice:ccovidservice,private router:Router) { }

  @Input()
  favoritedata: boolean;

  @Output()
  addToFavoriteList = new EventEmitter();
  
  ngOnInit(): void {
  }


  addToFavorite(ccountry: Ccountry) {
    console.log(ccountry)
    this.addToFavoriteList.emit(ccountry);
    console.log("clicked add to favourite...");

  }
  deleteFromFavourite(ccountry: Ccountry){
    console.log("clicked deleted to favourite...");
    console.log(ccountry);
    this.ccovidservice.deleteCountryFromFavouriteList(ccountry).subscribe(data=>{console.log(data);
    })
  }
  
}
