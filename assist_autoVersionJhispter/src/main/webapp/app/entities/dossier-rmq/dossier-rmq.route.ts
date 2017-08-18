import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes, CanActivate } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { JhiPaginationUtil } from 'ng-jhipster';

import { DossierRmqComponent } from './dossier-rmq.component';
import { DossierRmqDetailComponent } from './dossier-rmq-detail.component';
import { DossierRmqPopupComponent } from './dossier-rmq-dialog.component';
import { DossierRmqDeletePopupComponent } from './dossier-rmq-delete-dialog.component';

export const dossierRmqRoute: Routes = [
    {
        path: 'dossier-rmq',
        component: DossierRmqComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'assistanceApp.dossierRmq.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'dossier-rmq/:id',
        component: DossierRmqDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'assistanceApp.dossierRmq.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const dossierRmqPopupRoute: Routes = [
    {
        path: 'dossier-rmq-new',
        component: DossierRmqPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'assistanceApp.dossierRmq.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'dossier-rmq/:id/edit',
        component: DossierRmqPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'assistanceApp.dossierRmq.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'dossier-rmq/:id/delete',
        component: DossierRmqDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'assistanceApp.dossierRmq.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
