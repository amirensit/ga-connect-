import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AssistanceSharedModule } from '../../shared';
import {
    SysGouvernoratService,
    SysGouvernoratPopupService,
    SysGouvernoratComponent,
    SysGouvernoratDetailComponent,
    SysGouvernoratDialogComponent,
    SysGouvernoratPopupComponent,
    SysGouvernoratDeletePopupComponent,
    SysGouvernoratDeleteDialogComponent,
    sysGouvernoratRoute,
    sysGouvernoratPopupRoute,
} from './';

const ENTITY_STATES = [
    ...sysGouvernoratRoute,
    ...sysGouvernoratPopupRoute,
];

@NgModule({
    imports: [
        AssistanceSharedModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        SysGouvernoratComponent,
        SysGouvernoratDetailComponent,
        SysGouvernoratDialogComponent,
        SysGouvernoratDeleteDialogComponent,
        SysGouvernoratPopupComponent,
        SysGouvernoratDeletePopupComponent,
    ],
    entryComponents: [
        SysGouvernoratComponent,
        SysGouvernoratDialogComponent,
        SysGouvernoratPopupComponent,
        SysGouvernoratDeleteDialogComponent,
        SysGouvernoratDeletePopupComponent,
    ],
    providers: [
        SysGouvernoratService,
        SysGouvernoratPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AssistanceSysGouvernoratModule {}
