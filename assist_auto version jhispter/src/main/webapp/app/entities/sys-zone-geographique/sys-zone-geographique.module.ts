import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AssistanceSharedModule } from '../../shared';
import {
    SysZoneGeographiqueService,
    SysZoneGeographiquePopupService,
    SysZoneGeographiqueComponent,
    SysZoneGeographiqueDetailComponent,
    SysZoneGeographiqueDialogComponent,
    SysZoneGeographiquePopupComponent,
    SysZoneGeographiqueDeletePopupComponent,
    SysZoneGeographiqueDeleteDialogComponent,
    sysZoneGeographiqueRoute,
    sysZoneGeographiquePopupRoute,
} from './';

const ENTITY_STATES = [
    ...sysZoneGeographiqueRoute,
    ...sysZoneGeographiquePopupRoute,
];

@NgModule({
    imports: [
        AssistanceSharedModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        SysZoneGeographiqueComponent,
        SysZoneGeographiqueDetailComponent,
        SysZoneGeographiqueDialogComponent,
        SysZoneGeographiqueDeleteDialogComponent,
        SysZoneGeographiquePopupComponent,
        SysZoneGeographiqueDeletePopupComponent,
    ],
    entryComponents: [
        SysZoneGeographiqueComponent,
        SysZoneGeographiqueDialogComponent,
        SysZoneGeographiquePopupComponent,
        SysZoneGeographiqueDeleteDialogComponent,
        SysZoneGeographiqueDeletePopupComponent,
    ],
    providers: [
        SysZoneGeographiqueService,
        SysZoneGeographiquePopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AssistanceSysZoneGeographiqueModule {}
