/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { StrictHttpResponse } from '../../strict-http-response';
import { RequestBuilder } from '../../request-builder';

import { PageResponseFactureResponse } from '../../models/page-response-facture-response';

export interface GetFacturesByProjetId$Params {
  projetId: number;
  page?: number;
  size?: number;
}

export function getFacturesByProjetId(http: HttpClient, rootUrl: string, params: GetFacturesByProjetId$Params, context?: HttpContext): Observable<StrictHttpResponse<PageResponseFactureResponse>> {
  const rb = new RequestBuilder(rootUrl, getFacturesByProjetId.PATH, 'get');
  if (params) {
    rb.path('projetId', params.projetId, {});
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

getFacturesByProjetId.PATH = '/factures/projet/{projetId}';
