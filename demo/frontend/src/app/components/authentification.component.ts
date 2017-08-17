import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import {AuthService} from "../services/authentification.service";




@Component({
    selector: 'login',
    moduleId: module.id,
    templateUrl: 'authentification.component.html',
     providers:[AuthService]
  
    
})

export class LoginComponent  {
    model: any = {};
    modal: any = {};
    loading = false;
    error = '';
    users:any;
    //role:Role;
    selectedrole: any={};
    x:1;
    r:any;



    constructor(
        private router: Router,
        private authenticationService: AuthService
        ) { }

    

    login() {
        this.loading = true;
        this.authenticationService.login(this.model.login, this.model.password)
            .subscribe(result1 => {
                if (result1 >0) {
                        console.log(result1);
                                this.router.navigate(['/']);
               

                }

                     
                else {
                    this.error = 'Username or password is incorrect';
                    this.loading = false;
                }


                },
                error => {
                    this.error = 'Username or password is incorrect';
                   
                    console.log("errorrrr");
                    this.loading = false;
                    this.model.login='';
                    this.model.password='';
                    this.router.navigate(['/login']);


                }


            );
    }

    



}