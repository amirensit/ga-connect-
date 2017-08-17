import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes, CanActivate } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { JhiPaginationUtil } from 'ng-jhipster';

import { ContratComponent } from './contrat.component';
import { ContratDetailComponent } from './contrat-detail.component';
import { ContratPopupComponent } from './contrat-dialog.component';
import { ContratDeletePopupComponent } from './contrat-delete-dialog.component';
export const contratRoute: Routes = [
    {
        path: 'contrat',
        component: ContratComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'assistanceApp.contrat.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
     {
        path: 'contrat/:id',
        component: ContratDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'assistanceApp.contrat.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    
    

];

export const contratPopupRoute: Routes = [
    {
        path: 'contrat-new',
        component: ContratPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'assistanceApp.contrat.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'contrat/:id/edit',
        component: ContratPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'assistanceApp.contrat.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'contrat/:id/delete',
        component: ContratDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'assistanceApp.contrat.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
