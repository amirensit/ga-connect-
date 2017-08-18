import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';

import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { Contrat } from './contrat.model';
import { ContratPopupService } from './contrat-popup.service';
import { ContratService } from './contrat.service';
import { Voiture, VoitureService } from '../voiture';
import { Assuree, AssureeService } from '../assuree';
import { RefPack, RefPackService } from '../ref-pack';
import { RefCompagnie, RefCompagnieService } from '../ref-compagnie';
import { VoiturePopupService } from '../voiture/voiture-popup.service';
import { ResponseWrapper } from '../../shared';
import { RefMarque, RefMarqueService } from '../ref-marque';
import { Personne, PersonneService } from '../personne';
import { SysVille, SysVilleService } from '../sys-ville';

@Component({
    selector: 'jhi-contrat-dialog',
    templateUrl: './contrat-dialog.component.html',
    styleUrls: [
        'contrat-dialog.css'
    ]
})
export class ContratDialogComponent implements OnInit {

    contrat: Contrat={};
    isSaving: boolean;
    voitures: Voiture[];
    assurees: Assuree[];
    refpacks: RefPack[];
    refcompagnies: RefCompagnie[];
    debutDp: any;
    finDp: any;
    refmarques: RefMarque[];
    contrats: Contrat[];
    villes: SysVille[];
     a : any;
    
     

    constructor(
        public activeModal: NgbActiveModal,
        private alertService: JhiAlertService,
        private contratService: ContratService,
        private refMarqueService: RefMarqueService,
        private voitureService: VoitureService,
        private assureeService: AssureeService,
        private refPackService: RefPackService,
         private sysVilleService: SysVilleService,
        private refCompagnieService: RefCompagnieService,
        private eventManager: JhiEventManager,
        private personneService: PersonneService,
    ) {
    }

    ngOnInit() {
       
       if (this.contrat.id !== undefined) {
            this.a="update";
            
        } else {
            this.a="create";
            this.contrat.assure=new Assuree(null,{},null);
            
        }
        
        this.isSaving = false;
        
        this.contratService.query()
            .subscribe((res: ResponseWrapper) => { this.contrats = res.json; }, (res: ResponseWrapper) => this.onError(res.json));
        this.voitureService
            .query({filter: 'contrat-is-null'})
            .subscribe((res: ResponseWrapper) => {
                if (!this.contrat.voiture || !this.contrat.voiture.id ) {
                    this.voitures = res.json;
                    this.contrat.voiture = new Voiture(null,null,null,null,null,null);
                }
                else {
                    this.voitureService
                        .find(this.contrat.voiture.id)
                        .subscribe((subRes: Voiture) => {
                            this.voitures = [subRes].concat(res.json);
                        }, (subRes: ResponseWrapper) => this.onError(subRes.json));
                }
            }, (res: ResponseWrapper) => this.onError(res.json));

        this.sysVilleService
            .query({filter: 'personne-is-null'})
            .subscribe((res: ResponseWrapper) => {
                if (!this.contrat.assure.personne.ville || !this.contrat.assure.personne.ville.id) {
                    //this.contrat.assure.personne.ville=new SysVille(null,null,null);
                    this.villes = res.json;
                } else {
                    this.sysVilleService
                        .find(this.contrat.assure.personne.ville.id)
                        .subscribe((subRes: SysVille) => {
                            this.villes = [subRes].concat(res.json);
                        }, (subRes: ResponseWrapper) => this.onError(subRes.json));
                }
            }, (res: ResponseWrapper) => this.onError(res.json));
        this.assureeService.query()
            .subscribe((res: ResponseWrapper) => { this.assurees = res.json }, (res: ResponseWrapper) => this.onError(res.json));
        this.refPackService.query()
            .subscribe((res: ResponseWrapper) => { this.refpacks = res.json; }, (res: ResponseWrapper) => this.onError(res.json));
        this.refCompagnieService.query()
            .subscribe((res: ResponseWrapper) => { this.refcompagnies = res.json; }, (res: ResponseWrapper) => this.onError(res.json));
            this.refMarqueService.query()
            .subscribe((res: ResponseWrapper) => {  this.refmarques = res.json; }, (res: ResponseWrapper) => this.onError(res.json));
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }
    
    saveVoiture() {
        if (this.contrat.voiture.id !== undefined) {
            this.subscribeToSaveResponseVoiture(
                this.voitureService.update(this.contrat.voiture));
        } 
    }

    private subscribeToSaveResponseVoiture(result: Observable<Voiture>) {
        result.subscribe((res: Voiture) =>
            this.onSaveSuccessVoiture(res), (res: Response) => this.onSaveError(res));
    }

    private onSaveSuccessVoiture(result: Voiture) {
        this.eventManager.broadcast({ name   : 'voitureListModification', content: 'OK'});
       this.isSaving = false;
        
    }
    
    savePersonne() {
        if (this.contrat.assure.personne.id !== undefined) {
            this.subscribeToSaveResponsePersonne(
                this.personneService.update(this.contrat.assure.personne));
        } else {
            this.subscribeToSaveResponsePersonne(
                this.personneService.create(this.contrat.assure.personne));
        }
    }

    private subscribeToSaveResponsePersonne(result: Observable<Personne>) {
        result.subscribe((res: Personne) =>
            this.onSaveSuccessPersonne(res), (res: Response) => this.onSaveError(res));
    }

    private onSaveSuccessPersonne(result: Personne) {
        this.eventManager.broadcast({ name: 'personneListModification', content: 'OK'});
        this.isSaving = false;
        
    }

    saveAssure()
    {
        this.savePersonne();
        if (this.contrat.assure.id !== undefined) {
            this.subscribeToSaveResponse(
                this.assureeService.update(this.contrat.assure));
        } else {
            this.subscribeToSaveResponse(
                this.assureeService.create(this.contrat.assure));
        }

    }
    private subscribeToSaveResponseAssure(result: Observable<Assuree>) {
        result.subscribe((res: Assuree) =>
            this.onSaveSuccessAssure(res), (res: Response) => this.onSaveError(res));
    }

    private onSaveSuccessAssure(result: Assuree) {
        this.eventManager.broadcast({ name: 'assureeListModification', content: 'OK'});
        this.isSaving = false;
        
    }

    

    save() {
        this.saveAssure();
        this.saveVoiture();
        this.isSaving = true;

        if (this.contrat.id !== undefined) {
            this.subscribeToSaveResponse(
                this.contratService.update(this.contrat));
        } else {
            this.subscribeToSaveResponse(
                this.contratService.create(this.contrat));
        }
        
    }

    private subscribeToSaveResponse(result: Observable<Contrat>) {
        result.subscribe((res: Contrat) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError(res));
    }

    private onSaveSuccess(result: Contrat) {
        this.eventManager.broadcast({ name: 'contratListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError(error) {
        try {
            error.json();
        } catch (exception) {
            error.message = error.text();
        }
        this.isSaving = false;
        this.onError(error);
    }

    private onError(error) {
        this.alertService.error(error.message, null, null);
    }

    trackVoitureById(index: number, item: Voiture) {
        return item.id;
    }

    trackAssureeById(index: number, item: Assuree) {
        return item.id;
    }
    trackSysVilleById(index: number, item: SysVille) {
        return item.id;
    }

    trackRefPackById(index: number, item: RefPack) {
        return item.id;
    }

    trackRefMarqueById(index: number, item: RefMarque) {
        return item.id;
    }

    trackRefCompagnieById(index: number, item: RefCompagnie) {
        return item.id;
    }
}

@Component({
    selector: 'jhi-contrat-popup',
    template: ''
})
export class ContratPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private contratPopupService: ContratPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.contratPopupService
                    .open(ContratDialogComponent as Component, params['id']);
            } else {
                this.contratPopupService
                    .open(ContratDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}


