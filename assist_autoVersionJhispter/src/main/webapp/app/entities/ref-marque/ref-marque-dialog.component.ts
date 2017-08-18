import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { RefMarque } from './ref-marque.model';
import { RefMarquePopupService } from './ref-marque-popup.service';
import { RefMarqueService } from './ref-marque.service';

@Component({
    selector: 'jhi-ref-marque-dialog',
    templateUrl: './ref-marque-dialog.component.html'
})
export class RefMarqueDialogComponent implements OnInit {

    refMarque: RefMarque;
    isSaving: boolean;

    constructor(
        public activeModal: NgbActiveModal,
        private alertService: JhiAlertService,
        private refMarqueService: RefMarqueService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.refMarque.id !== undefined) {
            this.subscribeToSaveResponse(
                this.refMarqueService.update(this.refMarque));
        } else {
            this.subscribeToSaveResponse(
                this.refMarqueService.create(this.refMarque));
        }
    }

    private subscribeToSaveResponse(result: Observable<RefMarque>) {
        result.subscribe((res: RefMarque) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError(res));
    }

    private onSaveSuccess(result: RefMarque) {
        this.eventManager.broadcast({ name: 'refMarqueListModification', content: 'OK'});
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
}

@Component({
    selector: 'jhi-ref-marque-popup',
    template: ''
})
export class RefMarquePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private refMarquePopupService: RefMarquePopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.refMarquePopupService
                    .open(RefMarqueDialogComponent as Component, params['id']);
            } else {
                this.refMarquePopupService
                    .open(RefMarqueDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
