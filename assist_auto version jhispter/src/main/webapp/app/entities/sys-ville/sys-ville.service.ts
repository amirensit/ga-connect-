import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Rx';

import { SysVille } from './sys-ville.model';
import { ResponseWrapper, createRequestOption } from '../../shared';

@Injectable()
export class SysVilleService {

    private resourceUrl = 'api/sys-villes';
    private resourceSearchUrl = 'api/_search/sys-villes';

    constructor(private http: Http) { }

    create(sysVille: SysVille): Observable<SysVille> {
        const copy = this.convert(sysVille);
        return this.http.post(this.resourceUrl, copy).map((res: Response) => {
            return res.json();
        });
    }

    update(sysVille: SysVille): Observable<SysVille> {
        const copy = this.convert(sysVille);
        return this.http.put(this.resourceUrl, copy).map((res: Response) => {
            return res.json();
        });
    }

    find(id: number): Observable<SysVille> {
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

    private convert(sysVille: SysVille): SysVille {
        const copy: SysVille = Object.assign({}, sysVille);
        return copy;
    }
}
