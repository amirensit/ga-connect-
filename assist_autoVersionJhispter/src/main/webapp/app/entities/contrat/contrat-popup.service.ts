import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { Contrat } from './contrat.model';
import { ContratService } from './contrat.service';

@Injectable()
export class ContratPopupService {
    private ngbModalRef: NgbModalRef;

    constructor(
        private modalService: NgbModal,
        private router: Router,
        private contratService: ContratService

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
                this.contratService.find(id).subscribe((contrat) => {
                    if (contrat.debut) {
                        contrat.debut = {
                            year: contrat.debut.getFullYear(),
                            month: contrat.debut.getMonth() + 1,
                            day: contrat.debut.getDate()
                        };
                    }
                    if (contrat.fin) {
                        contrat.fin = {
                            year: contrat.fin.getFullYear(),
                            month: contrat.fin.getMonth() + 1,
                            day: contrat.fin.getDate()
                        };
                    }
                    this.ngbModalRef = this.contratModalRef(component, contrat);
                    resolve(this.ngbModalRef);
                });
            } else {
                // setTimeout used as a workaround for getting ExpressionChangedAfterItHasBeenCheckedError
                setTimeout(() => {
                    this.ngbModalRef = this.contratModalRef(component, new Contrat());
                    resolve(this.ngbModalRef);
                }, 0);
            }
        });
    }

    contratModalRef(component: Component, contrat: Contrat): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.contrat = contrat;
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
