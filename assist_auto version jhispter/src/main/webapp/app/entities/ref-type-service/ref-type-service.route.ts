import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes, CanActivate } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { JhiPaginationUtil } from 'ng-jhipster';

import { RefTypeServiceComponent } from './ref-type-service.component';
import { RefTypeServiceDetailComponent } from './ref-type-service-detail.component';
import { RefTypeServicePopupComponent } from './ref-type-service-dialog.component';
import { RefTypeServiceDeletePopupComponent } from './ref-type-service-delete-dialog.component';

export const refTypeServiceRoute: Routes = [
    {
        path: 'ref-type-service',
        component: RefTypeServiceComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'assistanceApp.refTypeService.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'ref-type-service/:id',
        component: RefTypeServiceDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'assistanceApp.refTypeService.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const refTypeServicePopupRoute: Routes = [
    {
        path: 'ref-type-service-new',
        component: RefTypeServicePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'assistanceApp.refTypeService.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'ref-type-service/:id/edit',
        component: RefTypeServicePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'assistanceApp.refTypeService.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'ref-type-service/:id/delete',
        component: RefTypeServiceDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'assistanceApp.refTypeService.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
