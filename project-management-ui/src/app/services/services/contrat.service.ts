/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { BaseService } from '../base-service';
import { ApiConfiguration } from '../api-configuration';
import { StrictHttpResponse } from '../strict-http-response';

import { ContratResponse } from '../models/contrat-response';
import { createContrat } from '../fn/contrat/create-contrat';
import { CreateContrat$Params } from '../fn/contrat/create-contrat';
import { getContratById } from '../fn/contrat/get-contrat-by-id';
import { GetContratById$Params } from '../fn/contrat/get-contrat-by-id';
import { getContratsByAppelOffreId } from '../fn/contrat/get-contrats-by-appel-offre-id';
import { GetContratsByAppelOffreId$Params } from '../fn/contrat/get-contrats-by-appel-offre-id';
import { getContratsByProjetId } from '../fn/contrat/get-contrats-by-projet-id';
import { GetContratsByProjetId$Params } from '../fn/contrat/get-contrats-by-projet-id';
import { getContratsByStructure } from '../fn/contrat/get-contrats-by-structure';
import { GetContratsByStructure$Params } from '../fn/contrat/get-contrats-by-structure';
import { PageResponseContratResponse } from '../models/page-response-contrat-response';

@Injectable({ providedIn: 'root' })
export class ContratService extends BaseService {
  constructor(config: ApiConfiguration, http: HttpClient) {
    super(config, http);
  }

  /** Path part for operation `createContrat()` */
  static readonly CreateContratPath = '/contrats';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `createContrat()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  createContrat$Response(params: CreateContrat$Params, context?: HttpContext): Observable<StrictHttpResponse<number>> {
    return createContrat(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `createContrat$Response()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  createContrat(params: CreateContrat$Params, context?: HttpContext): Observable<number> {
    return this.createContrat$Response(params, context).pipe(
      map((r: StrictHttpResponse<number>): number => r.body)
    );
  }

  /** Path part for operation `getContratById()` */
  static readonly GetContratByIdPath = '/contrats/{id}';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `getContratById()` instead.
   *
   * This method doesn't expect any request body.
   */
  getContratById$Response(params: GetContratById$Params, context?: HttpContext): Observable<StrictHttpResponse<ContratResponse>> {
    return getContratById(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `getContratById$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  getContratById(params: GetContratById$Params, context?: HttpContext): Observable<ContratResponse> {
    return this.getContratById$Response(params, context).pipe(
      map((r: StrictHttpResponse<ContratResponse>): ContratResponse => r.body)
    );
  }

  /** Path part for operation `getContratsByStructure()` */
  static readonly GetContratsByStructurePath = '/contrats/structure';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `getContratsByStructure()` instead.
   *
   * This method doesn't expect any request body.
   */
  getContratsByStructure$Response(params?: GetContratsByStructure$Params, context?: HttpContext): Observable<StrictHttpResponse<PageResponseContratResponse>> {
    return getContratsByStructure(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `getContratsByStructure$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  getContratsByStructure(params?: GetContratsByStructure$Params, context?: HttpContext): Observable<PageResponseContratResponse> {
    return this.getContratsByStructure$Response(params, context).pipe(
      map((r: StrictHttpResponse<PageResponseContratResponse>): PageResponseContratResponse => r.body)
    );
  }

  /** Path part for operation `getContratsByProjetId()` */
  static readonly GetContratsByProjetIdPath = '/contrats/projet/{projetId}';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `getContratsByProjetId()` instead.
   *
   * This method doesn't expect any request body.
   */
  getContratsByProjetId$Response(params: GetContratsByProjetId$Params, context?: HttpContext): Observable<StrictHttpResponse<PageResponseContratResponse>> {
    return getContratsByProjetId(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `getContratsByProjetId$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  getContratsByProjetId(params: GetContratsByProjetId$Params, context?: HttpContext): Observable<PageResponseContratResponse> {
    return this.getContratsByProjetId$Response(params, context).pipe(
      map((r: StrictHttpResponse<PageResponseContratResponse>): PageResponseContratResponse => r.body)
    );
  }

  /** Path part for operation `getContratsByAppelOffreId()` */
  static readonly GetContratsByAppelOffreIdPath = '/contrats/appelOffre/{appelOffreId}';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `getContratsByAppelOffreId()` instead.
   *
   * This method doesn't expect any request body.
   */
  getContratsByAppelOffreId$Response(params: GetContratsByAppelOffreId$Params, context?: HttpContext): Observable<StrictHttpResponse<PageResponseContratResponse>> {
    return getContratsByAppelOffreId(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `getContratsByAppelOffreId$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  getContratsByAppelOffreId(params: GetContratsByAppelOffreId$Params, context?: HttpContext): Observable<PageResponseContratResponse> {
    return this.getContratsByAppelOffreId$Response(params, context).pipe(
      map((r: StrictHttpResponse<PageResponseContratResponse>): PageResponseContratResponse => r.body)
    );
  }

}
