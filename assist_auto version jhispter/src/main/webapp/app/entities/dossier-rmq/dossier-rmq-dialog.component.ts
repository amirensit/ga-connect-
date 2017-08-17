import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { DossierRmq } from './dossier-rmq.model';
import { DossierRmqPopupService } from './dossier-rmq-popup.service';
import { DossierRmqService } from './dossier-rmq.service';
import { Assuree, AssureeService } from '../assuree';
import { RefTypeService, RefTypeServiceService } from '../ref-type-service';
import { SysVille, SysVilleService } from '../sys-ville';
import { RefRemorqueur, RefRemorqueurService } from '../ref-remorqueur';
import { ResponseWrapper } from '../../shared';
import { Personne, PersonneService } from '../personne';
@Component({
    selector: 'jhi-dossier-rmq-dialog',
    templateUrl: './dossier-rmq-dialog.component.html'
})
export class DossierRmqDialogComponent implements OnInit {

    dossierRmq: DossierRmq;
    isSaving: boolean;

    assurees: Assuree[];

    typeservices: RefTypeService[];

    sysvilles: SysVille[];

    refremorqueurs: RefRemorqueur[];

    constructor(
        public activeModal: NgbActiveModal,
        private alertService: JhiAlertService,
        private dossierRmqService: DossierRmqService,
        private assureeService: AssureeService,
        private refTypeServiceService: RefTypeServiceService,
        private sysVilleService: SysVilleService,
        private refRemorqueurService: RefRemorqueurService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.dossierRmq.assuree=new Assuree(null,new Personne(),null);
        this.dossierRmq.typeService=new RefTypeService();
        this.isSaving = false;
        this.assureeService
            .query({filter: 'dossierrmq-is-null'})
            .subscribe((res: ResponseWrapper) => {
                if (!this.dossierRmq.assuree || !this.dossierRmq.assuree.id) {
                    this.assurees = res.json;
                } else {
                    this.assureeService
                        .find(this.dossierRmq.assuree.id)
                        .subscribe((subRes: Assuree) => {
                            this.assurees = [subRes].concat(res.json);
                        }, (subRes: ResponseWrapper) => this.onError(subRes.json));
                }
            }, (res: ResponseWrapper) => this.onError(res.json));
        this.refTypeServiceService
            .query({filter: 'dossierrmq-is-null'})
            .subscribe((res: ResponseWrapper) => {
                if (!this.dossierRmq.typeService || !this.dossierRmq.typeService.id) {
                    this.typeservices = res.json;
                } else {
                    this.refTypeServiceService
                        .find(this.dossierRmq.typeService.id)
                        .subscribe((subRes: RefTypeService) => {
                            this.typeservices = [subRes].concat(res.json);
                        }, (subRes: ResponseWrapper) => this.onError(subRes.json));
                }
            }, (res: ResponseWrapper) => this.onError(res.json));
        this.sysVilleService.query()
            .subscribe((res: ResponseWrapper) => { this.sysvilles = res.json; }, (res: ResponseWrapper) => this.onError(res.json));
        this.refRemorqueurService.query()
            .subscribe((res: ResponseWrapper) => { this.refremorqueurs = res.json; }, (res: ResponseWrapper) => this.onError(res.json));
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.dossierRmq.id !== undefined) {
            this.subscribeToSaveResponse(
                this.dossierRmqService.update(this.dossierRmq));
        } else {
            this.subscribeToSaveResponse(
                this.dossierRmqService.create(this.dossierRmq));
        }
    }

    private subscribeToSaveResponse(result: Observable<DossierRmq>) {
        result.subscribe((res: DossierRmq) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError(res));
    }

    private onSaveSuccess(result: DossierRmq) {
        this.eventManager.broadcast({ name: 'dossierRmqListModification', content: 'OK'});
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

    trackAssureeById(index: number, item: Assuree) {
        return item.id;
    }

    trackRefTypeServiceById(index: number, item: RefTypeService) {
        return item.id;
    }

    trackSysVilleById(index: number, item: SysVille) {
        return item.id;
    }

    trackRefRemorqueurById(index: number, item: RefRemorqueur) {
        return item.id;
    }
}

@Component({
    selector: 'jhi-dossier-rmq-popup',
    template: ''
})
export class DossierRmqPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private dossierRmqPopupService: DossierRmqPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.dossierRmqPopupService
                    .open(DossierRmqDialogComponent as Component, params['id']);
            } else {
                this.dossierRmqPopupService
                    .open(DossierRmqDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
