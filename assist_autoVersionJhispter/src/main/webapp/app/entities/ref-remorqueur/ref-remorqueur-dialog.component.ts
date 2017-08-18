import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { RefRemorqueur } from './ref-remorqueur.model';
import { RefRemorqueurPopupService } from './ref-remorqueur-popup.service';
import { RefRemorqueurService } from './ref-remorqueur.service';
import { Contact, ContactService } from '../contact';
import { ResponseWrapper } from '../../shared';
import { Personne, PersonneService } from '../personne';
@Component({
    selector: 'jhi-ref-remorqueur-dialog',
    templateUrl: './ref-remorqueur-dialog.component.html'
})
export class RefRemorqueurDialogComponent implements OnInit {

    refRemorqueur: RefRemorqueur;
    isSaving: boolean;

    contacts: Contact[];

    constructor(
        public activeModal: NgbActiveModal,
        private alertService: JhiAlertService,
        private refRemorqueurService: RefRemorqueurService,
        private contactService: ContactService,
         private personneService: PersonneService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {

        if (this.refRemorqueur.id === undefined) {
           this.refRemorqueur.contact = new Contact(null,null,{},null);
        } 
        this.isSaving = false;
        this.contactService.query()
            .subscribe((res: ResponseWrapper) => { this.contacts = res.json; }, (res: ResponseWrapper) => this.onError(res.json));
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }
   savePersonne() {
        if (this.refRemorqueur.contact.personne.id !== undefined) {
            this.subscribeToSaveResponsePersonne(
                this.personneService.update(this.refRemorqueur.contact.personne));
        } else {
            this.subscribeToSaveResponsePersonne(
                this.personneService.create(this.refRemorqueur.contact.personne));
        }
    }

    private subscribeToSaveResponsePersonne(result: Observable<Personne>) {
        result.subscribe((res: Personne) =>
            this.onSaveSuccessPersonne(res), (res: Response) => this.onSaveError(res));
    }

    private onSaveSuccessPersonne(result: Personne) {
        this.eventManager.broadcast({ name: 'personneListModification', content: 'OK'});
        this.isSaving = false;
        
    }

    save() {
      
        this.savePersonne();
        this.isSaving = true;
        if (this.refRemorqueur.id !== undefined) {
            this.subscribeToSaveResponse(
                this.refRemorqueurService.update(this.refRemorqueur));
        } else {
            this.subscribeToSaveResponse(
                this.refRemorqueurService.create(this.refRemorqueur));
        }
    }

    private subscribeToSaveResponse(result: Observable<RefRemorqueur>) {
        result.subscribe((res: RefRemorqueur) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError(res));
    }

    private onSaveSuccess(result: RefRemorqueur) {
        this.eventManager.broadcast({ name: 'refRemorqueurListModification', content: 'OK'});
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

    trackContactById(index: number, item: Contact) {
        return item.id;
    }
}

@Component({
    selector: 'jhi-ref-remorqueur-popup',
    template: ''
})
export class RefRemorqueurPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private refRemorqueurPopupService: RefRemorqueurPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.refRemorqueurPopupService
                    .open(RefRemorqueurDialogComponent as Component, params['id']);
            } else {
                this.refRemorqueurPopupService
                    .open(RefRemorqueurDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
