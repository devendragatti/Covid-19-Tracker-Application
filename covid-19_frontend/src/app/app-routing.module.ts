import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CountrylistComponent } from './countrylist/countrylist.component';
import { EdituserComponent } from './edituser/edituser.component';
import { FavoriteComponent } from './favorite/favorite.component';
import { HeaderComponent } from './header/header.component';
import { HomeComponent } from './home/home.component';
import { HomeheadnavComponent } from './homeheadnav/homeheadnav.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { SearchcountryComponent } from './searchcountry/searchcountry.component';
import { AuthGuard } from './services/auth.guard';
import { UpdatepasswordComponent } from './updatepassword/updatepassword.component';
import { UserfootnavComponent } from './userfootnav/userfootnav.component';
import { UserheadnavComponent } from './userheadnav/userheadnav.component';

const routes: Routes = [
  {
    path: "login",
    component:LoginComponent,
    canActivate: [AuthGuard]
  },
  {
    path: "head",
    component:UserheadnavComponent,
    canActivate: [AuthGuard]
  },
  {
    path: "foot",
    component:UserfootnavComponent,
    canActivate: [AuthGuard]
  },
  {
    path: "home",
    component:HomeComponent,
    canActivate: [AuthGuard]
  },
  {
    path: "updatepassword",
    component:UpdatepasswordComponent,canActivate: [AuthGuard]
  },
  {
    path: "edituser",
    component:EdituserComponent,canActivate: [AuthGuard]
  },
  {
    path: "favorite",
    component:FavoriteComponent,canActivate: [AuthGuard]
  },
  {
    path: "header",
    component:HeaderComponent,canActivate: [AuthGuard]
  },
  {
    path: "register",
    component:RegisterComponent,canActivate: [AuthGuard]
  },
  {
    path: "countrylist",
    component:CountrylistComponent,canActivate: [AuthGuard]
  },
  {
    path: "findcountry",
    component:SearchcountryComponent,canActivate: [AuthGuard]
  },
  {
    path: "",
    component:HomeComponent,
    pathMatch:'full'
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
  providers: [AuthGuard]
})
export class AppRoutingModule { }
