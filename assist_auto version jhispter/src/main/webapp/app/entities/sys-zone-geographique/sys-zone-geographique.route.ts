import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes, CanActivate } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { JhiPaginationUtil } from 'ng-jhipster';

import { SysZoneGeographiqueComponent } from './sys-zone-geographique.component';
import { SysZoneGeographiqueDetailComponent } from './sys-zone-geographique-detail.component';
import { SysZoneGeographiquePopupComponent } from './sys-zone-geographique-dialog.component';
import { SysZoneGeographiqueDeletePopupComponent } from './sys-zone-geographique-delete-dialog.component';

export const sysZoneGeographiqueRoute: Routes = [
    {
        path: 'sys-zone-geographique',
        component: SysZoneGeographiqueComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'assistanceApp.sysZoneGeographique.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'sys-zone-geographique/:id',
        component: SysZoneGeographiqueDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'assistanceApp.sysZoneGeographique.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const sysZoneGeographiquePopupRoute: Routes = [
    {
        path: 'sys-zone-geographique-new',
        component: SysZoneGeographiquePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'assistanceApp.sysZoneGeographique.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'sys-zone-geographique/:id/edit',
        component: SysZoneGeographiquePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'assistanceApp.sysZoneGeographique.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'sys-zone-geographique/:id/delete',
        component: SysZoneGeographiqueDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'assistanceApp.sysZoneGeographique.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
