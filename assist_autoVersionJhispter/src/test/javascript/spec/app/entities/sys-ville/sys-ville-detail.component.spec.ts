/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject } from '@angular/core/testing';
import { OnInit } from '@angular/core';
import { DatePipe } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Rx';
import { JhiDateUtils, JhiDataUtils, JhiEventManager } from 'ng-jhipster';
import { AssistanceTestModule } from '../../../test.module';
import { MockActivatedRoute } from '../../../helpers/mock-route.service';
import { SysVilleDetailComponent } from '../../../../../../main/webapp/app/entities/sys-ville/sys-ville-detail.component';
import { SysVilleService } from '../../../../../../main/webapp/app/entities/sys-ville/sys-ville.service';
import { SysVille } from '../../../../../../main/webapp/app/entities/sys-ville/sys-ville.model';

describe('Component Tests', () => {

    describe('SysVille Management Detail Component', () => {
        let comp: SysVilleDetailComponent;
        let fixture: ComponentFixture<SysVilleDetailComponent>;
        let service: SysVilleService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [AssistanceTestModule],
                declarations: [SysVilleDetailComponent],
                providers: [
                    JhiDateUtils,
                    JhiDataUtils,
                    DatePipe,
                    {
                        provide: ActivatedRoute,
                        useValue: new MockActivatedRoute({id: 123})
                    },
                    SysVilleService,
                    JhiEventManager
                ]
            }).overrideTemplate(SysVilleDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(SysVilleDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SysVilleService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
            // GIVEN

            spyOn(service, 'find').and.returnValue(Observable.of(new SysVille(10)));

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.find).toHaveBeenCalledWith(123);
            expect(comp.sysVille).toEqual(jasmine.objectContaining({id: 10}));
            });
        });
    });

});
