/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { StrictHttpResponse } from '../../strict-http-response';
import { RequestBuilder } from '../../request-builder';

import { PageResponseFactureResponse } from '../../models/page-response-facture-response';

export interface GetFacturesByContratId$Params {
  contratId: number;
  page?: number;
  size?: number;
}

export function getFacturesByContratId(http: HttpClient, rootUrl: string, params: GetFacturesByContratId$Params, context?: HttpContext): Observable<StrictHttpResponse<PageResponseFactureResponse>> {
  const rb = new RequestBuilder(rootUrl, getFacturesByContratId.PATH, 'get');
  if (params) {
    rb.path('contratId', params.contratId, {});
    rb.query('page', params.page, {});
    rb.query('size', params.size, {});
  }

  return http.request(
    rb.build({ responseType: 'json', accept: 'application/json', context })
  ).pipe(
    filter((r: any): r is HttpResponse<any> => r instanceof HttpResponse),
    map((r: HttpResponse<any>) => {
      return r as StrictHttpResponse<PageResponseFactureResponse>;
    })
  );
}

getFacturesByContratId.PATH = '/factures/contrat/{contratId}';
