import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes, CanActivate } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { JhiPaginationUtil } from 'ng-jhipster';

import { RefMarqueComponent } from './ref-marque.component';
import { RefMarqueDetailComponent } from './ref-marque-detail.component';
import { RefMarquePopupComponent } from './ref-marque-dialog.component';
import { RefMarqueDeletePopupComponent } from './ref-marque-delete-dialog.component';

export const refMarqueRoute: Routes = [
    {
        path: 'ref-marque',
        component: RefMarqueComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'assistanceApp.refMarque.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'ref-marque/:id',
        component: RefMarqueDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'assistanceApp.refMarque.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const refMarquePopupRoute: Routes = [
    {
        path: 'ref-marque-new',
        component: RefMarquePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'assistanceApp.refMarque.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'ref-marque/:id/edit',
        component: RefMarquePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'assistanceApp.refMarque.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'ref-marque/:id/delete',
        component: RefMarqueDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'assistanceApp.refMarque.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
