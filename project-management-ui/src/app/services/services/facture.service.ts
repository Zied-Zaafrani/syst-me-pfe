/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { BaseService } from '../base-service';
import { ApiConfiguration } from '../api-configuration';
import { StrictHttpResponse } from '../strict-http-response';

import { createFacture } from '../fn/facture/create-facture';
import { CreateFacture$Params } from '../fn/facture/create-facture';
import { FactureResponse } from '../models/facture-response';
import { getFactureById } from '../fn/facture/get-facture-by-id';
import { GetFactureById$Params } from '../fn/facture/get-facture-by-id';
import { getFacturesByAppelOffreId } from '../fn/facture/get-factures-by-appel-offre-id';
import { GetFacturesByAppelOffreId$Params } from '../fn/facture/get-factures-by-appel-offre-id';
import { getFacturesByContratId } from '../fn/facture/get-factures-by-contrat-id';
import { GetFacturesByContratId$Params } from '../fn/facture/get-factures-by-contrat-id';
import { getFacturesByProjetId } from '../fn/facture/get-factures-by-projet-id';
import { GetFacturesByProjetId$Params } from '../fn/facture/get-factures-by-projet-id';
import { getFacturesByStructureId } from '../fn/facture/get-factures-by-structure-id';
import { GetFacturesByStructureId$Params } from '../fn/facture/get-factures-by-structure-id';
import { PageResponseFactureResponse } from '../models/page-response-facture-response';

@Injectable({ providedIn: 'root' })
export class FactureService extends BaseService {
  constructor(config: ApiConfiguration, http: HttpClient) {
    super(config, http);
  }

  /** Path part for operation `createFacture()` */
  static readonly CreateFacturePath = '/factures';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `createFacture()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  createFacture$Response(params: CreateFacture$Params, context?: HttpContext): Observable<StrictHttpResponse<FactureResponse>> {
    return createFacture(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `createFacture$Response()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  createFacture(params: CreateFacture$Params, context?: HttpContext): Observable<FactureResponse> {
    return this.createFacture$Response(params, context).pipe(
      map((r: StrictHttpResponse<FactureResponse>): FactureResponse => r.body)
    );
  }

  /** Path part for operation `getFactureById()` */
  static readonly GetFactureByIdPath = '/factures/{id}';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `getFactureById()` instead.
   *
   * This method doesn't expect any request body.
   */
  getFactureById$Response(params: GetFactureById$Params, context?: HttpContext): Observable<StrictHttpResponse<FactureResponse>> {
    return getFactureById(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `getFactureById$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  getFactureById(params: GetFactureById$Params, context?: HttpContext): Observable<FactureResponse> {
    return this.getFactureById$Response(params, context).pipe(
      map((r: StrictHttpResponse<FactureResponse>): FactureResponse => r.body)
    );
  }

  /** Path part for operation `getFacturesByStructureId()` */
  static readonly GetFacturesByStructureIdPath = '/factures/structure';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `getFacturesByStructureId()` instead.
   *
   * This method doesn't expect any request body.
   */
  getFacturesByStructureId$Response(params?: GetFacturesByStructureId$Params, context?: HttpContext): Observable<StrictHttpResponse<PageResponseFactureResponse>> {
    return getFacturesByStructureId(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `getFacturesByStructureId$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  getFacturesByStructureId(params?: GetFacturesByStructureId$Params, context?: HttpContext): Observable<PageResponseFactureResponse> {
    return this.getFacturesByStructureId$Response(params, context).pipe(
      map((r: StrictHttpResponse<PageResponseFactureResponse>): PageResponseFactureResponse => r.body)
    );
  }

  /** Path part for operation `getFacturesByProjetId()` */
  static readonly GetFacturesByProjetIdPath = '/factures/projet/{projetId}';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `getFacturesByProjetId()` instead.
   *
   * This method doesn't expect any request body.
   */
  getFacturesByProjetId$Response(params: GetFacturesByProjetId$Params, context?: HttpContext): Observable<StrictHttpResponse<PageResponseFactureResponse>> {
    return getFacturesByProjetId(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `getFacturesByProjetId$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  getFacturesByProjetId(params: GetFacturesByProjetId$Params, context?: HttpContext): Observable<PageResponseFactureResponse> {
    return this.getFacturesByProjetId$Response(params, context).pipe(
      map((r: StrictHttpResponse<PageResponseFactureResponse>): PageResponseFactureResponse => r.body)
    );
  }

  /** Path part for operation `getFacturesByContratId()` */
  static readonly GetFacturesByContratIdPath = '/factures/contrat/{contratId}';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `getFacturesByContratId()` instead.
   *
   * This method doesn't expect any request body.
   */
  getFacturesByContratId$Response(params: GetFacturesByContratId$Params, context?: HttpContext): Observable<StrictHttpResponse<PageResponseFactureResponse>> {
    return getFacturesByContratId(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `getFacturesByContratId$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  getFacturesByContratId(params: GetFacturesByContratId$Params, context?: HttpContext): Observable<PageResponseFactureResponse> {
    return this.getFacturesByContratId$Response(params, context).pipe(
      map((r: StrictHttpResponse<PageResponseFactureResponse>): PageResponseFactureResponse => r.body)
    );
  }

  /** Path part for operation `getFacturesByAppelOffreId()` */
  static readonly GetFacturesByAppelOffreIdPath = '/factures/appelOffre/{appelOffreId}';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `getFacturesByAppelOffreId()` instead.
   *
   * This method doesn't expect any request body.
   */
  getFacturesByAppelOffreId$Response(params: GetFacturesByAppelOffreId$Params, context?: HttpContext): Observable<StrictHttpResponse<PageResponseFactureResponse>> {
    return getFacturesByAppelOffreId(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `getFacturesByAppelOffreId$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  getFacturesByAppelOffreId(params: GetFacturesByAppelOffreId$Params, context?: HttpContext): Observable<PageResponseFactureResponse> {
    return this.getFacturesByAppelOffreId$Response(params, context).pipe(
      map((r: StrictHttpResponse<PageResponseFactureResponse>): PageResponseFactureResponse => r.body)
    );
  }

}
