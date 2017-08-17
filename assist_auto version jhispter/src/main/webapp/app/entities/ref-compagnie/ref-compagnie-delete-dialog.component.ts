import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { RefCompagnie } from './ref-compagnie.model';
import { RefCompagniePopupService } from './ref-compagnie-popup.service';
import { RefCompagnieService } from './ref-compagnie.service';

@Component({
    selector: 'jhi-ref-compagnie-delete-dialog',
    templateUrl: './ref-compagnie-delete-dialog.component.html'
})
export class RefCompagnieDeleteDialogComponent {

    refCompagnie: RefCompagnie;

    constructor(
        private refCompagnieService: RefCompagnieService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.refCompagnieService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'refCompagnieListModification',
                content: 'Deleted an refCompagnie'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-ref-compagnie-delete-popup',
    template: ''
})
export class RefCompagnieDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private refCompagniePopupService: RefCompagniePopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.refCompagniePopupService
                .open(RefCompagnieDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
