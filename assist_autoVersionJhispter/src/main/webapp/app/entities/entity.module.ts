import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { AssistanceDossierRmqModule } from './dossier-rmq/dossier-rmq.module';
import { AssistanceRefCompagnieModule } from './ref-compagnie/ref-compagnie.module';
import { AssistanceRefPackModule } from './ref-pack/ref-pack.module';
import { AssistanceRefRemorqueurModule } from './ref-remorqueur/ref-remorqueur.module';
import { AssistancePersonneModule } from './personne/personne.module';
import { AssistanceContactModule } from './contact/contact.module';
import { AssistanceAssureeModule } from './assuree/assuree.module';
import { AssistanceContratModule } from './contrat/contrat.module';
import { AssistanceVoitureModule } from './voiture/voiture.module';
import { AssistanceRefMarqueModule } from './ref-marque/ref-marque.module';
import { AssistanceRefTypeServiceModule } from './ref-type-service/ref-type-service.module';
import { AssistanceSysVilleModule } from './sys-ville/sys-ville.module';
import { AssistanceSysZoneGeographiqueModule } from './sys-zone-geographique/sys-zone-geographique.module';
import { AssistanceSysGouvernoratModule } from './sys-gouvernorat/sys-gouvernorat.module';
/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    imports: [
        AssistanceDossierRmqModule,
        AssistanceRefCompagnieModule,
        AssistanceRefPackModule,
        AssistanceRefRemorqueurModule,
        AssistancePersonneModule,
        AssistanceContactModule,
        AssistanceAssureeModule,
        AssistanceContratModule,
        AssistanceVoitureModule,
        AssistanceRefMarqueModule,
        AssistanceRefTypeServiceModule,
        AssistanceSysVilleModule,
        AssistanceSysZoneGeographiqueModule,
        AssistanceSysGouvernoratModule,
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AssistanceEntityModule {}
