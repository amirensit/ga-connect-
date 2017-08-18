import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes, CanActivate } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { JhiPaginationUtil } from 'ng-jhipster';

import { VoitureComponent } from './voiture.component';
import { VoitureDetailComponent } from './voiture-detail.component';
import { VoiturePopupComponent } from './voiture-dialog.component';
import { VoitureDeletePopupComponent } from './voiture-delete-dialog.component';

export const voitureRoute: Routes = [
    {
        path: 'voiture',
        component: VoitureComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'assistanceApp.voiture.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'voiture/:id',
        component: VoitureDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'assistanceApp.voiture.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const voiturePopupRoute: Routes = [
    {
        path: 'voiture-new',
        component: VoiturePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'assistanceApp.voiture.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'voiture/:id/edit',
        component: VoiturePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'assistanceApp.voiture.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'voiture/:id/delete',
        component: VoitureDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'assistanceApp.voiture.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
