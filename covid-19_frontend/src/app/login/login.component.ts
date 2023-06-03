import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ccovidservice } from '../ccovidservice.service';
import { User } from '../User';
export const TOKEN_NAME = "token";
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
 user: User;
  dataa: any;
  username: any;
  disp_msg: string;
  constructor(private ccovidservice: ccovidservice, private router: Router) {
    this.user=new User();
   }

  ngOnInit(): void {
  }
  login() {
    console.log("User----->"+this.user.userName);
    this.ccovidservice.getToken(this.user).subscribe(
      (data=>{
        this.dataa=data;
        console.log(this.dataa);
        localStorage.setItem(TOKEN_NAME, this.dataa['token']);
        localStorage.setItem("username",this.user.userName)
        console.log("updated-->"+TOKEN_NAME);

      console.log("token is:"+this.dataa['token']) ;
      console.log("Login Successfull!");
     // localStorage.setItem(tk,data['token']);
      //console.log("tk--->"+tk);

      // delay(6000)
      localStorage.setItem("isloggedin","true")
      this.router.navigate(["/header"]);
      }),
      (error=>{console.log("Error!--Token not generated because of Invalid credentials");
      this.disp_msg="Invalid username and password";
      })

    )
   
  }
  
}
