/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { StrictHttpResponse } from '../../strict-http-response';
import { RequestBuilder } from '../../request-builder';

import { PageResponseFactureResponse } from '../../models/page-response-facture-response';

export interface GetFacturesByAppelOffreId$Params {
  appelOffreId: number;
  page?: number;
  size?: number;
}

export function getFacturesByAppelOffreId(http: HttpClient, rootUrl: string, params: GetFacturesByAppelOffreId$Params, context?: HttpContext): Observable<StrictHttpResponse<PageResponseFactureResponse>> {
  const rb = new RequestBuilder(rootUrl, getFacturesByAppelOffreId.PATH, 'get');
  if (params) {
    rb.path('appelOffreId', params.appelOffreId, {});
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

getFacturesByAppelOffreId.PATH = '/factures/appelOffre/{appelOffreId}';
