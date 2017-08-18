import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Rx';

import { DossierRmq } from './dossier-rmq.model';
import { ResponseWrapper, createRequestOption } from '../../shared';

@Injectable()
export class DossierRmqService {

    private resourceUrl = 'api/dossier-rmqs';
    private resourceSearchUrl = 'api/_search/dossier-rmqs';

    constructor(private http: Http) { }

    create(dossierRmq: DossierRmq): Observable<DossierRmq> {
        const copy = this.convert(dossierRmq);
        return this.http.post(this.resourceUrl, copy).map((res: Response) => {
            return res.json();
        });
    }

    update(dossierRmq: DossierRmq): Observable<DossierRmq> {
        const copy = this.convert(dossierRmq);
        return this.http.put(this.resourceUrl, copy).map((res: Response) => {
            return res.json();
        });
    }

    find(id: number): Observable<DossierRmq> {
        return this.http.get(`${this.resourceUrl}/${id}`).map((res: Response) => {
            return res.json();
        });
    }

    query(req?: any): Observable<ResponseWrapper> {
        const options = createRequestOption(req);
        return this.http.get(this.resourceUrl, options)
            .map((res: Response) => this.convertResponse(res));
    }

    delete(id: number): Observable<Response> {
        return this.http.delete(`${this.resourceUrl}/${id}`);
    }

    search(req?: any): Observable<ResponseWrapper> {
        const options = createRequestOption(req);
        return this.http.get(this.resourceSearchUrl, options)
            .map((res: any) => this.convertResponse(res));
    }

    private convertResponse(res: Response): ResponseWrapper {
        const jsonResponse = res.json();
        return new ResponseWrapper(res.headers, jsonResponse, res.status);
    }

    private convert(dossierRmq: DossierRmq): DossierRmq {
        const copy: DossierRmq = Object.assign({}, dossierRmq);
        return copy;
    }
}
