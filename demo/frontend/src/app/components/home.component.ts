import { Component } from '@angular/core';
import { GetsService } from '../services/gets.service';
import {AuthService} from "../services/authentification.service";
import { Router } from '@angular/router';
import{AuthGuard} from '../guard/auth.guard';

@Component({
  moduleId: module.id,
  selector: 'home',
  templateUrl: './home.component.html',
  providers:[AuthService,AuthGuard]
 
})
export class Home {


    constructor(
        private router: Router,
        private authenticationService: AuthService
        ) { }

 
logout()
    {
        this.authenticationService.logout();
        this.router.navigate(['/login']);

    }







}



 

