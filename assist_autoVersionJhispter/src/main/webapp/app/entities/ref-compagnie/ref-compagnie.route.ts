import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes, CanActivate } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { JhiPaginationUtil } from 'ng-jhipster';

import { RefCompagnieComponent } from './ref-compagnie.component';
import { RefCompagnieDetailComponent } from './ref-compagnie-detail.component';
import { RefCompagniePopupComponent } from './ref-compagnie-dialog.component';
import { RefCompagnieDeletePopupComponent } from './ref-compagnie-delete-dialog.component';

export const refCompagnieRoute: Routes = [
    {
        path: 'ref-compagnie',
        component: RefCompagnieComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'assistanceApp.refCompagnie.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'ref-compagnie/:id',
        component: RefCompagnieDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'assistanceApp.refCompagnie.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const refCompagniePopupRoute: Routes = [
    {
        path: 'ref-compagnie-new',
        component: RefCompagniePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'assistanceApp.refCompagnie.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'ref-compagnie/:id/edit',
        component: RefCompagniePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'assistanceApp.refCompagnie.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'ref-compagnie/:id/delete',
        component: RefCompagnieDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'assistanceApp.refCompagnie.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
