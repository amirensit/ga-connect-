import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { Contact } from './contact.model';
import { ContactPopupService } from './contact-popup.service';
import { ContactService } from './contact.service';
import { Personne, PersonneService } from '../personne';
import { ResponseWrapper } from '../../shared';

@Component({
    selector: 'jhi-contact-dialog',
    templateUrl: './contact-dialog.component.html'
})
export class ContactDialogComponent implements OnInit {

    contact: Contact;
    isSaving: boolean;

    personnes: Personne[];

    constructor(
        public activeModal: NgbActiveModal,
        private alertService: JhiAlertService,
        private contactService: ContactService,
        private personneService: PersonneService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.personneService
            .query({filter: 'contact-is-null'})
            .subscribe((res: ResponseWrapper) => {
                if (!this.contact.personne || !this.contact.personne.id) {
                    this.personnes = res.json;
                } else {
                    this.personneService
                        .find(this.contact.personne.id)
                        .subscribe((subRes: Personne) => {
                            this.personnes = [subRes].concat(res.json);
                        }, (subRes: ResponseWrapper) => this.onError(subRes.json));
                }
            }, (res: ResponseWrapper) => this.onError(res.json));
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.contact.id !== undefined) {
            this.subscribeToSaveResponse(
                this.contactService.update(this.contact));
        } else {
            this.subscribeToSaveResponse(
                this.contactService.create(this.contact));
        }
    }

    private subscribeToSaveResponse(result: Observable<Contact>) {
        result.subscribe((res: Contact) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError(res));
    }

    private onSaveSuccess(result: Contact) {
        this.eventManager.broadcast({ name: 'contactListModification', content: 'OK'});
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

    trackPersonneById(index: number, item: Personne) {
        return item.id;
    }
}

@Component({
    selector: 'jhi-contact-popup',
    template: ''
})
export class ContactPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private contactPopupService: ContactPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.contactPopupService
                    .open(ContactDialogComponent as Component, params['id']);
            } else {
                this.contactPopupService
                    .open(ContactDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
