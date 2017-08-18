import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AssistanceSharedModule } from '../../shared';
import {
    SysVilleService,
    SysVillePopupService,
    SysVilleComponent,
    SysVilleDetailComponent,
    SysVilleDialogComponent,
    SysVillePopupComponent,
    SysVilleDeletePopupComponent,
    SysVilleDeleteDialogComponent,
    sysVilleRoute,
    sysVillePopupRoute,
} from './';

const ENTITY_STATES = [
    ...sysVilleRoute,
    ...sysVillePopupRoute,
];

@NgModule({
    imports: [
        AssistanceSharedModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        SysVilleComponent,
        SysVilleDetailComponent,
        SysVilleDialogComponent,
        SysVilleDeleteDialogComponent,
        SysVillePopupComponent,
        SysVilleDeletePopupComponent,
    ],
    entryComponents: [
        SysVilleComponent,
        SysVilleDialogComponent,
        SysVillePopupComponent,
        SysVilleDeleteDialogComponent,
        SysVilleDeletePopupComponent,
    ],
    providers: [
        SysVilleService,
        SysVillePopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AssistanceSysVilleModule {}
