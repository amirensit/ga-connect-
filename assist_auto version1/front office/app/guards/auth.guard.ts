/**
 * Created by DELL on 28/04/2017.
 */
import { Injectable } from '@angular/core';
import { Router, CanActivate } from '@angular/router';

@Injectable()
export class AuthGuard implements CanActivate {

    constructor(private router: Router) { }

    canActivate() {
        if (localStorage.getItem('currentUser')) {
            // logged in so return true
            return true;
        }
        console.log("check");
        console.log(localStorage.getItem('currentUser'));
        // not logged in so redirect to login page
        this.router.navigate(['/login']);
        return false;
    }

   
}