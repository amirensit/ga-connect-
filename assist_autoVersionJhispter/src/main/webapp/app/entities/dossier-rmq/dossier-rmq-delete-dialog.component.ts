import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { DossierRmq } from './dossier-rmq.model';
import { DossierRmqPopupService } from './dossier-rmq-popup.service';
import { DossierRmqService } from './dossier-rmq.service';

@Component({
    selector: 'jhi-dossier-rmq-delete-dialog',
    templateUrl: './dossier-rmq-delete-dialog.component.html'
})
export class DossierRmqDeleteDialogComponent {

    dossierRmq: DossierRmq;

    constructor(
        private dossierRmqService: DossierRmqService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.dossierRmqService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'dossierRmqListModification',
                content: 'Deleted an dossierRmq'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-dossier-rmq-delete-popup',
    template: ''
})
export class DossierRmqDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private dossierRmqPopupService: DossierRmqPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.dossierRmqPopupService
                .open(DossierRmqDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
