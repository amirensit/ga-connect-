import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Rx';

import { RefPack } from './ref-pack.model';
import { ResponseWrapper, createRequestOption } from '../../shared';

@Injectable()
export class RefPackService {

    private resourceUrl = 'api/ref-packs';
    private resourceSearchUrl = 'api/_search/ref-packs';

    constructor(private http: Http) { }

    create(refPack: RefPack): Observable<RefPack> {
        const copy = this.convert(refPack);
        return this.http.post(this.resourceUrl, copy).map((res: Response) => {
            return res.json();
        });
    }

    update(refPack: RefPack): Observable<RefPack> {
        const copy = this.convert(refPack);
        return this.http.put(this.resourceUrl, copy).map((res: Response) => {
            return res.json();
        });
    }

    find(id: number): Observable<RefPack> {
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

    private convert(refPack: RefPack): RefPack {
        const copy: RefPack = Object.assign({}, refPack);
        return copy;
    }
}
