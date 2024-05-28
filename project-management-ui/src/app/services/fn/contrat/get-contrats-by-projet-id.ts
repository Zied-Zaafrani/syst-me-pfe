/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { StrictHttpResponse } from '../../strict-http-response';
import { RequestBuilder } from '../../request-builder';

import { PageResponseContratResponse } from '../../models/page-response-contrat-response';

export interface GetContratsByProjetId$Params {
  projetId: number;
  page?: number;
  size?: number;
}

export function getContratsByProjetId(http: HttpClient, rootUrl: string, params: GetContratsByProjetId$Params, context?: HttpContext): Observable<StrictHttpResponse<PageResponseContratResponse>> {
  const rb = new RequestBuilder(rootUrl, getContratsByProjetId.PATH, 'get');
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
      return r as StrictHttpResponse<PageResponseContratResponse>;
    })
  );
}

getContratsByProjetId.PATH = '/contrats/projet/{projetId}';
