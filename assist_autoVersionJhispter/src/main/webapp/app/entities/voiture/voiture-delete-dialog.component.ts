import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { Voiture } from './voiture.model';
import { VoiturePopupService } from './voiture-popup.service';
import { VoitureService } from './voiture.service';

@Component({
    selector: 'jhi-voiture-delete-dialog',
    templateUrl: './voiture-delete-dialog.component.html'
})
export class VoitureDeleteDialogComponent {

    voiture: Voiture;

    constructor(
        private voitureService: VoitureService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.voitureService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'voitureListModification',
                content: 'Deleted an voiture'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-voiture-delete-popup',
    template: ''
})
export class VoitureDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private voiturePopupService: VoiturePopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.voiturePopupService
                .open(VoitureDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
