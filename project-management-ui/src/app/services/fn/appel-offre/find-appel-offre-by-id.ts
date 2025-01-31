/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { StrictHttpResponse } from '../../strict-http-response';
import { RequestBuilder } from '../../request-builder';

import { AppelOffreResponse } from '../../models/appel-offre-response';

export interface FindAppelOffreById$Params {
  appelOffreId: number;
}

export function findAppelOffreById(http: HttpClient, rootUrl: string, params: FindAppelOffreById$Params, context?: HttpContext): Observable<StrictHttpResponse<AppelOffreResponse>> {
  const rb = new RequestBuilder(rootUrl, findAppelOffreById.PATH, 'get');
  if (params) {
    rb.path('appelOffreId', params.appelOffreId, {});
  }

  return http.request(
    rb.build({ responseType: 'json', accept: 'application/json', context })
  ).pipe(
    filter((r: any): r is HttpResponse<any> => r instanceof HttpResponse),
    map((r: HttpResponse<any>) => {
      return r as StrictHttpResponse<AppelOffreResponse>;
    })
  );
}

findAppelOffreById.PATH = '/appels-offres/{appelOffreId}';
