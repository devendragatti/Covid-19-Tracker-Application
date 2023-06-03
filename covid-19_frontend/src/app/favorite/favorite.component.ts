import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Ccountry } from '../ccountry';
import { ccovidservice } from '../ccovidservice.service';

@Component({
  selector: 'app-favorite',
  templateUrl: './favorite.component.html',
  styleUrls: ['./favorite.component.css']
})
export class FavoriteComponent implements OnInit {

  countries: Array<Ccountry>;
  favoritedata: boolean = true;

  constructor(private ccovidservice:ccovidservice,private router:Router) { }

  ngOnInit(): void {
    this.ccovidservice.getFavoriteList().subscribe(data => {
      this.countries = data;
    });

  }
}
