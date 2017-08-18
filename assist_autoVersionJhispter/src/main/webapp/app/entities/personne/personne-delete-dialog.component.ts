import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { Personne } from './personne.model';
import { PersonnePopupService } from './personne-popup.service';
import { PersonneService } from './personne.service';

@Component({
    selector: 'jhi-personne-delete-dialog',
    templateUrl: './personne-delete-dialog.component.html'
})
export class PersonneDeleteDialogComponent {

    personne: Personne;

    constructor(
        private personneService: PersonneService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.personneService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'personneListModification',
                content: 'Deleted an personne'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-personne-delete-popup',
    template: ''
})
export class PersonneDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private personnePopupService: PersonnePopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.personnePopupService
                .open(PersonneDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
