import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes, CanActivate } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { JhiPaginationUtil } from 'ng-jhipster';

import { RefPackComponent } from './ref-pack.component';
import { RefPackDetailComponent } from './ref-pack-detail.component';
import { RefPackPopupComponent } from './ref-pack-dialog.component';
import { RefPackDeletePopupComponent } from './ref-pack-delete-dialog.component';

export const refPackRoute: Routes = [
    {
        path: 'ref-pack',
        component: RefPackComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'assistanceApp.refPack.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'ref-pack/:id',
        component: RefPackDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'assistanceApp.refPack.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const refPackPopupRoute: Routes = [
    {
        path: 'ref-pack-new',
        component: RefPackPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'assistanceApp.refPack.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'ref-pack/:id/edit',
        component: RefPackPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'assistanceApp.refPack.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'ref-pack/:id/delete',
        component: RefPackDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'assistanceApp.refPack.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
