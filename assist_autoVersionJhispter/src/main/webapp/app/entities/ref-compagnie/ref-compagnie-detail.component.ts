import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager  } from 'ng-jhipster';

import { RefCompagnie } from './ref-compagnie.model';
import { RefCompagnieService } from './ref-compagnie.service';

@Component({
    selector: 'jhi-ref-compagnie-detail',
    templateUrl: './ref-compagnie-detail.component.html'
})
export class RefCompagnieDetailComponent implements OnInit, OnDestroy {

    refCompagnie: RefCompagnie;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private refCompagnieService: RefCompagnieService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInRefCompagnies();
    }

    load(id) {
        this.refCompagnieService.find(id).subscribe((refCompagnie) => {
            this.refCompagnie = refCompagnie;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInRefCompagnies() {
        this.eventSubscriber = this.eventManager.subscribe(
            'refCompagnieListModification',
            (response) => this.load(this.refCompagnie.id)
        );
    }
}
