import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { MODULE_COMPONENTS, MODULE_ROUTES } from './dashboard.routes';
import {BrowserModule} from "@angular/platform-browser";
import {FormsModule, ReactiveFormsModule,} from "@angular/forms";
import {CommonModule} from '@angular/common';
import { Ng2FilterPipeModule } from 'ng2-filter-pipe';
import { CustomFormsModule } from 'ng2-validation'
import { FlashMessagesModule } from 'angular2-flash-messages';
import {DataTableModule} from "angular2-datatable"; 


@NgModule({
    imports: [
        CommonModule,
        BrowserModule,
        FormsModule,
        FlashMessagesModule,
        CustomFormsModule,
        Ng2FilterPipeModule,
        DataTableModule,
        
        ReactiveFormsModule,
        RouterModule.forChild(MODULE_ROUTES)
    ],
    declarations: [ MODULE_COMPONENTS ]
})

export class DashboardModule{}
