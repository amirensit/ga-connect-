import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { RefRemorqueur } from './ref-remorqueur.model';
import { RefRemorqueurService } from './ref-remorqueur.service';

@Injectable()
export class RefRemorqueurPopupService {
    private ngbModalRef: NgbModalRef;

    constructor(
        private modalService: NgbModal,
        private router: Router,
        private refRemorqueurService: RefRemorqueurService

    ) {
        this.ngbModalRef = null;
    }

    open(component: Component, id?: number | any): Promise<NgbModalRef> {
        return new Promise<NgbModalRef>((resolve, reject) => {
            const isOpen = this.ngbModalRef !== null;
            if (isOpen) {
                resolve(this.ngbModalRef);
            }

            if (id) {
                this.refRemorqueurService.find(id).subscribe((refRemorqueur) => {
                    this.ngbModalRef = this.refRemorqueurModalRef(component, refRemorqueur);
                    resolve(this.ngbModalRef);
                });
            } else {
                // setTimeout used as a workaround for getting ExpressionChangedAfterItHasBeenCheckedError
                setTimeout(() => {
                    this.ngbModalRef = this.refRemorqueurModalRef(component, new RefRemorqueur());
                    resolve(this.ngbModalRef);
                }, 0);
            }
        });
    }

    refRemorqueurModalRef(component: Component, refRemorqueur: RefRemorqueur): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.refRemorqueur = refRemorqueur;
        modalRef.result.then((result) => {
            this.router.navigate([{ outlets: { popup: null }}], { replaceUrl: true });
            this.ngbModalRef = null;
        }, (reason) => {
            this.router.navigate([{ outlets: { popup: null }}], { replaceUrl: true });
            this.ngbModalRef = null;
        });
        return modalRef;
    }
}
