/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject } from '@angular/core/testing';
import { OnInit } from '@angular/core';
import { DatePipe } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Rx';
import { JhiDateUtils, JhiDataUtils, JhiEventManager } from 'ng-jhipster';
import { AssistanceTestModule } from '../../../test.module';
import { MockActivatedRoute } from '../../../helpers/mock-route.service';
import { ContratDetailComponent } from '../../../../../../main/webapp/app/entities/contrat/contrat-detail.component';
import { ContratService } from '../../../../../../main/webapp/app/entities/contrat/contrat.service';
import { Contrat } from '../../../../../../main/webapp/app/entities/contrat/contrat.model';

describe('Component Tests', () => {

    describe('Contrat Management Detail Component', () => {
        let comp: ContratDetailComponent;
        let fixture: ComponentFixture<ContratDetailComponent>;
        let service: ContratService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [AssistanceTestModule],
                declarations: [ContratDetailComponent],
                providers: [
                    JhiDateUtils,
                    JhiDataUtils,
                    DatePipe,
                    {
                        provide: ActivatedRoute,
                        useValue: new MockActivatedRoute({id: 123})
                    },
                    ContratService,
                    JhiEventManager
                ]
            }).overrideTemplate(ContratDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(ContratDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ContratService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
            // GIVEN

            spyOn(service, 'find').and.returnValue(Observable.of(new Contrat(10)));

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.find).toHaveBeenCalledWith(123);
            expect(comp.contrat).toEqual(jasmine.objectContaining({id: 10}));
            });
        });
    });

});
