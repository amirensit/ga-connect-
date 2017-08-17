import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes, CanActivate } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { JhiPaginationUtil } from 'ng-jhipster';

import { SysGouvernoratComponent } from './sys-gouvernorat.component';
import { SysGouvernoratDetailComponent } from './sys-gouvernorat-detail.component';
import { SysGouvernoratPopupComponent } from './sys-gouvernorat-dialog.component';
import { SysGouvernoratDeletePopupComponent } from './sys-gouvernorat-delete-dialog.component';

export const sysGouvernoratRoute: Routes = [
    {
        path: 'sys-gouvernorat',
        component: SysGouvernoratComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'assistanceApp.sysGouvernorat.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'sys-gouvernorat/:id',
        component: SysGouvernoratDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'assistanceApp.sysGouvernorat.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const sysGouvernoratPopupRoute: Routes = [
    {
        path: 'sys-gouvernorat-new',
        component: SysGouvernoratPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'assistanceApp.sysGouvernorat.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'sys-gouvernorat/:id/edit',
        component: SysGouvernoratPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'assistanceApp.sysGouvernorat.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'sys-gouvernorat/:id/delete',
        component: SysGouvernoratDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'assistanceApp.sysGouvernorat.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
