import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes, CanActivate } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { JhiPaginationUtil } from 'ng-jhipster';

import { AssureeComponent } from './assuree.component';
import { AssureeDetailComponent } from './assuree-detail.component';
import { AssureePopupComponent } from './assuree-dialog.component';
import { AssureeDeletePopupComponent } from './assuree-delete-dialog.component';

export const assureeRoute: Routes = [
    {
        path: 'assuree',
        component: AssureeComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'assistanceApp.assuree.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'assuree/:id',
        component: AssureeDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'assistanceApp.assuree.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const assureePopupRoute: Routes = [
    {
        path: 'assuree-new',
        component: AssureePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'assistanceApp.assuree.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'assuree/:id/edit',
        component: AssureePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'assistanceApp.assuree.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'assuree/:id/delete',
        component: AssureeDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'assistanceApp.assuree.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
