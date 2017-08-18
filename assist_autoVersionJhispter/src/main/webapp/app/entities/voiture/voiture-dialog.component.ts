import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { Voiture } from './voiture.model';
import { VoiturePopupService } from './voiture-popup.service';
import { VoitureService } from './voiture.service';
import { RefMarque, RefMarqueService } from '../ref-marque';
import { Contrat, ContratService } from '../contrat';
import { ResponseWrapper } from '../../shared';

@Component({
    selector: 'jhi-voiture-dialog',
    templateUrl: './voiture-dialog.component.html'
})
export class VoitureDialogComponent implements OnInit {

    public voiture: Voiture;
    public isSaving: boolean;

    public refmarques: RefMarque[];

   public contrats: Contrat[];
    public miseCirculationDp: any;

    constructor(
        public activeModal: NgbActiveModal,
        public alertService: JhiAlertService,
        public voitureService: VoitureService,
        public refMarqueService: RefMarqueService,
        public contratService: ContratService,
        public eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.refMarqueService.query()
            .subscribe((res: ResponseWrapper) => { this.refmarques = res.json; }, (res: ResponseWrapper) => this.onError(res.json));
        this.contratService.query()
            .subscribe((res: ResponseWrapper) => { this.contrats = res.json; }, (res: ResponseWrapper) => this.onError(res.json));
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.voiture.id !== undefined) {
            this.subscribeToSaveResponse(
                this.voitureService.update(this.voiture));
        } else {
            this.subscribeToSaveResponse(
                this.voitureService.create(this.voiture));
        }
    }

    public subscribeToSaveResponse(result: Observable<Voiture>) {
        result.subscribe((res: Voiture) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError(res));
    }

    public onSaveSuccess(result: Voiture) {
        this.eventManager.broadcast({ name: 'voitureListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    public onSaveError(error) {
        try {
            error.json();
        } catch (exception) {
            error.message = error.text();
        }
        this.isSaving = false;
        this.onError(error);
    }

    public onError(error) {
        this.alertService.error(error.message, null, null);
    }

    public trackRefMarqueById(index: number, item: RefMarque) {
        return item.id;
    }

    public trackContratById(index: number, item: Contrat) {
        return item.id;
    }
}

@Component({
    selector: 'jhi-voiture-popup',
    template: ''
})
export class VoiturePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private voiturePopupService: VoiturePopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.voiturePopupService
                    .open(VoitureDialogComponent as Component, params['id']);
            } else {
                this.voiturePopupService
                    .open(VoitureDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
