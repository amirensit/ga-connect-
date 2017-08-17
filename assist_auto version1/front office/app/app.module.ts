import { NgModule }      from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { RouterModule } from '@angular/router';
import { HttpModule } from '@angular/http';
import { CustomFormsModule } from 'ng2-validation';

import { AppComponent }   from './app.component';
import { DashboardComponent } from './dashboard/dashboard.component';

import { DashboardModule } from './dashboard/dashboard.module';
import { SidebarModule } from './sidebar/sidebar.module';
import { FooterModule } from './shared/footer/footer.module';
import { NavbarModule} from './shared/navbar/navbar.module';

import { HashLocationStrategy, LocationStrategy ,CommonModule} from '@angular/common';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {DataTableModule} from "angular2-datatable"; 



import {ClientService} from "./services/client.service";
import {ContratService} from "./services/contrat.service";
import {RemorqueurService} from "./services/remorqueur.service";
import {PackService} from "./services/pack.service";
import {MarqueService} from "./services/marque.service";
import {TypeServiceService} from "./services/typeService.service";
import {GouvernoratService} from "./services/gouvernorat.service";
import {VilleService} from "./services/ville.service";
import { Ng2FilterPipeModule } from 'ng2-filter-pipe';
import { FlashMessagesModule } from 'angular2-flash-messages';
import {rolesService} from "./services/role.service";
import {utilisateursService} from "./services/utilisateur.service";
import { AuthenticationService} from "./services/authentication.service";
import {AuthGuard} from "./guards/auth.guard";

@NgModule({
    imports:      [
        CommonModule,
        HttpModule,
        FormsModule,
        FlashMessagesModule,
        ReactiveFormsModule,
        BrowserModule,
        DashboardModule,
        SidebarModule,
        NavbarModule,
        FooterModule,
        CustomFormsModule,
        Ng2FilterPipeModule,
        DataTableModule,
       
        
        RouterModule.forRoot([])
    ],
    declarations: [ AppComponent, DashboardComponent ],
    providers: [{provide: LocationStrategy, useClass: HashLocationStrategy},AuthenticationService,ContratService,utilisateursService,rolesService,AuthGuard,ClientService,PackService,MarqueService,TypeServiceService,GouvernoratService,VilleService,RemorqueurService],
    bootstrap:    [ AppComponent ]
})
export class AppModule { }
