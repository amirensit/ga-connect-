import { Component, OnInit } from '@angular/core';
import { ROUTES } from './sidebar-routes.config';

declare var $:any;
@Component({
    moduleId: module.id,
    selector: 'sidebar-cmp',
    templateUrl: 'sidebar.component.html',
})

export class SidebarComponent implements OnInit {
    public menuItems: any[];
    private currentUser: any;
    ngOnInit() {
        $.getScript('../../assets/js/sidebar-moving-tab.js');
        this.menuItems = ROUTES.filter(menuItem => menuItem);
         this.currentUser = JSON.parse(localStorage.getItem('currentUser'));
        console.log('navbar.component',this.currentUser);
    }
}
