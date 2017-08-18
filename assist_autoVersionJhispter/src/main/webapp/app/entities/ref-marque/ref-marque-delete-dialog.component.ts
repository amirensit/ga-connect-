import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { RefMarque } from './ref-marque.model';
import { RefMarquePopupService } from './ref-marque-popup.service';
import { RefMarqueService } from './ref-marque.service';

@Component({
    selector: 'jhi-ref-marque-delete-dialog',
    templateUrl: './ref-marque-delete-dialog.component.html'
})
export class RefMarqueDeleteDialogComponent {

    refMarque: RefMarque;

    constructor(
        private refMarqueService: RefMarqueService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.refMarqueService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'refMarqueListModification',
                content: 'Deleted an refMarque'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-ref-marque-delete-popup',
    template: ''
})
export class RefMarqueDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private refMarquePopupService: RefMarquePopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.refMarquePopupService
                .open(RefMarqueDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
