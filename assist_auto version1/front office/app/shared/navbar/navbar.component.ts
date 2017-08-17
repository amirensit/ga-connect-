import { Component, OnInit } from '@angular/core';
import { ROUTES } from '../.././sidebar/sidebar-routes.config';
import {Location, LocationStrategy, PathLocationStrategy} from '@angular/common';
import {AuthenticationService} from "../.././services/authentication.service";
import { Router } from "@angular/router";
@Component({
    moduleId: module.id,
    selector: 'navbar-cmp',
    templateUrl: 'navbar.component.html'
})

export class NavbarComponent implements OnInit{
    private listTitles: any[];
    location: Location;
     private currentUser: any;
    constructor(location:Location,  private authenticationService: AuthenticationService,private route :Router) {
        this.location = location;
    }
    ngOnInit(){
        this.listTitles = ROUTES.filter(listTitle => listTitle);
        this.currentUser = JSON.parse(localStorage.getItem('currentUser'));
        console.log('navbar.component',this.currentUser);
    }
    getTitle(){
        var titlee = this.location.prepareExternalUrl(this.location.path());
        if(titlee.charAt(0) === '#'){
            titlee = titlee.slice( 2 );
        }
        for(var item = 0; item < this.listTitles.length; item++){
            if(this.listTitles[item].path === titlee){
                return this.listTitles[item].title;
            }
        }
        return 'Dashboard';
    }
logout(id){
this.authenticationService.logout(id).subscribe(result=>{console.log("c bn")});
this.route.navigate(['/login']);
window.location.reload();


}
    
}
