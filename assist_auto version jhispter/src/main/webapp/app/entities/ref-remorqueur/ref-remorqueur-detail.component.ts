import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager  } from 'ng-jhipster';

import { RefRemorqueur } from './ref-remorqueur.model';
import { RefRemorqueurService } from './ref-remorqueur.service';

@Component({
    selector: 'jhi-ref-remorqueur-detail',
    templateUrl: './ref-remorqueur-detail.component.html'
})
export class RefRemorqueurDetailComponent implements OnInit, OnDestroy {

    refRemorqueur: RefRemorqueur;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private refRemorqueurService: RefRemorqueurService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInRefRemorqueurs();
    }

    load(id) {
        this.refRemorqueurService.find(id).subscribe((refRemorqueur) => {
            this.refRemorqueur = refRemorqueur;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInRefRemorqueurs() {
        this.eventSubscriber = this.eventManager.subscribe(
            'refRemorqueurListModification',
            (response) => this.load(this.refRemorqueur.id)
        );
    }
}
