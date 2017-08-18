import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager  } from 'ng-jhipster';

import { RefMarque } from './ref-marque.model';
import { RefMarqueService } from './ref-marque.service';

@Component({
    selector: 'jhi-ref-marque-detail',
    templateUrl: './ref-marque-detail.component.html'
})
export class RefMarqueDetailComponent implements OnInit, OnDestroy {

    refMarque: RefMarque;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private refMarqueService: RefMarqueService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInRefMarques();
    }

    load(id) {
        this.refMarqueService.find(id).subscribe((refMarque) => {
            this.refMarque = refMarque;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInRefMarques() {
        this.eventSubscriber = this.eventManager.subscribe(
            'refMarqueListModification',
            (response) => this.load(this.refMarque.id)
        );
    }
}
