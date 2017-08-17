import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AssistanceSharedModule } from '../../shared';
import {
    VoitureService,
    VoiturePopupService,
    VoitureComponent,
    VoitureDetailComponent,
    VoitureDialogComponent,
    VoiturePopupComponent,
    VoitureDeletePopupComponent,
    VoitureDeleteDialogComponent,
    voitureRoute,
    voiturePopupRoute,
} from './';

const ENTITY_STATES = [
    ...voitureRoute,
    ...voiturePopupRoute,
];

@NgModule({
    imports: [
        AssistanceSharedModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        VoitureComponent,
        VoitureDetailComponent,
        VoitureDialogComponent,
        VoitureDeleteDialogComponent,
        VoiturePopupComponent,
        VoitureDeletePopupComponent,
    ],
    entryComponents: [
        VoitureComponent,
        VoitureDialogComponent,
        VoiturePopupComponent,
        VoitureDeleteDialogComponent,
        VoitureDeletePopupComponent,
    ],
    providers: [
        VoitureService,
        VoiturePopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AssistanceVoitureModule {}
