import {Injectable} from '@angular/core';
import {Http} from '@angular/http';
import { Router,CanActivate } from '@angular/router';
import {Headers,RequestOptions} from '@angular/http';

 
@Injectable ()
export class AuthGuard implements CanActivate {


constructor(private router :Router) {}

canActivate(){

    if(localStorage.getItem('currentUser'))
    return true;
    this.router.navigate(['/login']);
    return false;

}


}