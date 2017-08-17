import { Component, HostBinding } from '@angular/core';
import {OnInit } from '@angular/core';
@Component({
    moduleId: module.id,
    selector: 'footer-cmp',
    templateUrl: 'footer.component.html'
})

export class FooterComponent implements OnInit{
    test : Date = new Date();
 private currentUser: any;
      ngOnInit(){
       
        this.currentUser = JSON.parse(localStorage.getItem('currentUser'));
        console.log('navbar.component',this.currentUser);
    }
}
 