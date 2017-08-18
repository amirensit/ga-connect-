import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { RefRemorqueur } from './ref-remorqueur.model';
import { RefRemorqueurPopupService } from './ref-remorqueur-popup.service';
import { RefRemorqueurService } from './ref-remorqueur.service';

@Component({
    selector: 'jhi-ref-remorqueur-delete-dialog',
    templateUrl: './ref-remorqueur-delete-dialog.component.html'
})
export class RefRemorqueurDeleteDialogComponent {

    refRemorqueur: RefRemorqueur;

    constructor(
        private refRemorqueurService: RefRemorqueurService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.refRemorqueurService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'refRemorqueurListModification',
                content: 'Deleted an refRemorqueur'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-ref-remorqueur-delete-popup',
    template: ''
})
export class RefRemorqueurDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private refRemorqueurPopupService: RefRemorqueurPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.refRemorqueurPopupService
                .open(RefRemorqueurDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
