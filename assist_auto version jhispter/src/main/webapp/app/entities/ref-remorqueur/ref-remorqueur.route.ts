import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes, CanActivate } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { JhiPaginationUtil } from 'ng-jhipster';

import { RefRemorqueurComponent } from './ref-remorqueur.component';
import { RefRemorqueurDetailComponent } from './ref-remorqueur-detail.component';
import { RefRemorqueurPopupComponent } from './ref-remorqueur-dialog.component';
import { RefRemorqueurDeletePopupComponent } from './ref-remorqueur-delete-dialog.component';

export const refRemorqueurRoute: Routes = [
    {
        path: 'ref-remorqueur',
        component: RefRemorqueurComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'assistanceApp.refRemorqueur.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'ref-remorqueur/:id',
        component: RefRemorqueurDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'assistanceApp.refRemorqueur.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const refRemorqueurPopupRoute: Routes = [
    {
        path: 'ref-remorqueur-new',
        component: RefRemorqueurPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'assistanceApp.refRemorqueur.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'ref-remorqueur/:id/edit',
        component: RefRemorqueurPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'assistanceApp.refRemorqueur.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'ref-remorqueur/:id/delete',
        component: RefRemorqueurDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'assistanceApp.refRemorqueur.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
