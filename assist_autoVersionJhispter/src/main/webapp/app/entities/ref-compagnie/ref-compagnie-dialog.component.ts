import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { RefCompagnie } from './ref-compagnie.model';
import { RefCompagniePopupService } from './ref-compagnie-popup.service';
import { RefCompagnieService } from './ref-compagnie.service';
import { SysVille, SysVilleService } from '../sys-ville';
import { RefPack, RefPackService } from '../ref-pack';
import { ResponseWrapper } from '../../shared';

@Component({
    selector: 'jhi-ref-compagnie-dialog',
    templateUrl: './ref-compagnie-dialog.component.html'
})
export class RefCompagnieDialogComponent implements OnInit {

    refCompagnie: RefCompagnie;
    isSaving: boolean;

    villes: SysVille[];

    refpacks: RefPack[];

    constructor(
        public activeModal: NgbActiveModal,
        private alertService: JhiAlertService,
        private refCompagnieService: RefCompagnieService,
        private sysVilleService: SysVilleService,
        private refPackService: RefPackService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.sysVilleService
            .query({filter: 'refcompagnie-is-null'})
            .subscribe((res: ResponseWrapper) => {
                if (!this.refCompagnie.ville || !this.refCompagnie.ville.id) {
                    this.villes = res.json;
                } else {
                    this.sysVilleService
                        .find(this.refCompagnie.ville.id)
                        .subscribe((subRes: SysVille) => {
                            this.villes = [subRes].concat(res.json);
                        }, (subRes: ResponseWrapper) => this.onError(subRes.json));
                }
            }, (res: ResponseWrapper) => this.onError(res.json));
        this.refPackService.query()
            .subscribe((res: ResponseWrapper) => { this.refpacks = res.json; }, (res: ResponseWrapper) => this.onError(res.json));
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.refCompagnie.id !== undefined) {
            this.subscribeToSaveResponse(
                this.refCompagnieService.update(this.refCompagnie));
        } else {
            this.subscribeToSaveResponse(
                this.refCompagnieService.create(this.refCompagnie));
        }
    }

    private subscribeToSaveResponse(result: Observable<RefCompagnie>) {
        result.subscribe((res: RefCompagnie) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError(res));
    }

    private onSaveSuccess(result: RefCompagnie) {
        this.eventManager.broadcast({ name: 'refCompagnieListModification', content: 'OK'});
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

    trackSysVilleById(index: number, item: SysVille) {
        return item.id;
    }

    trackRefPackById(index: number, item: RefPack) {
        return item.id;
    }

    getSelected(selectedVals: Array<any>, option: any) {
        if (selectedVals) {
            for (let i = 0; i < selectedVals.length; i++) {
                if (option.id === selectedVals[i].id) {
                    return selectedVals[i];
                }
            }
        }
        return option;
    }
}

@Component({
    selector: 'jhi-ref-compagnie-popup',
    template: ''
})
export class RefCompagniePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private refCompagniePopupService: RefCompagniePopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.refCompagniePopupService
                    .open(RefCompagnieDialogComponent as Component, params['id']);
            } else {
                this.refCompagniePopupService
                    .open(RefCompagnieDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
