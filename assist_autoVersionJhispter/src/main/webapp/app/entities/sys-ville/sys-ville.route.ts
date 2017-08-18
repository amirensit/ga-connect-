import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes, CanActivate } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { JhiPaginationUtil } from 'ng-jhipster';

import { SysVilleComponent } from './sys-ville.component';
import { SysVilleDetailComponent } from './sys-ville-detail.component';
import { SysVillePopupComponent } from './sys-ville-dialog.component';
import { SysVilleDeletePopupComponent } from './sys-ville-delete-dialog.component';

export const sysVilleRoute: Routes = [
    {
        path: 'sys-ville',
        component: SysVilleComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'assistanceApp.sysVille.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'sys-ville/:id',
        component: SysVilleDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'assistanceApp.sysVille.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const sysVillePopupRoute: Routes = [
    {
        path: 'sys-ville-new',
        component: SysVillePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'assistanceApp.sysVille.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'sys-ville/:id/edit',
        component: SysVillePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'assistanceApp.sysVille.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'sys-ville/:id/delete',
        component: SysVilleDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'assistanceApp.sysVille.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
