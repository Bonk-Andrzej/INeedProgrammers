import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {AuthComponent} from './auth/auth.component';
import {PasswordResetComponent} from './auth/password-reset/password-reset.component';
import {SignInComponent} from './auth/sign-in/sign-in.component';
import {BenefitComponent} from './benefit/benefit.component';
import {CategoryComponent} from './category/category.component';
import {LocationComponent} from './location/location.component';
import {SeniorityComponent} from './seniority/seniority.component';
import {TechnologyComponent} from './technology/technology.component';
import {UserComponent} from './user/user.component';
import {UserModule} from './user/user.module';
import {BenefitEditComponent} from './benefit/benefit-edit/benefit-edit.component';
import {BenefitIndexComponent} from './benefit/benefit-index/benefit-index.component';
import {BenefitNewComponent} from './benefit/benefit-new/benefit-new.component';
import {CategoryIndexComponent} from './category/category-index/category-index.component';
import {CategoryEditComponent} from './category/category-edit/category-edit.component';
import {CategoryNewComponent} from './category/category-new/category-new.component';
import {LocationIndexComponent} from './location/location-index/location-index.component';
import {LocationEditComponent} from './location/location-edit/location-edit.component';
import {LocationNewComponent} from './location/location-new/location-new.component';
import {SeniorityIndexComponent} from './seniority/seniority-index/seniority-index.component';
import {SeniorityNewComponent} from './seniority/seniority-new/seniority-new.component';
import {SeniorityEditComponent} from './seniority/seniority-edit/seniority-edit.component';
import {TechnologyIndexComponent} from './technology/technology-index/technology-index.component';
import {TechnologyEditComponent} from './technology/technology-edit/technology-edit.component';
import {TechnologyNewComponent} from './technology/technology-new/technology-new.component';
import {JobOfferComponent} from './job-offer/job-offer.component';
import {JobOfferNewComponent} from './job-offer/job-offer-new/job-offer-new.component';
import {JobOfferIndexComponent} from './job-offer/job-offer-index/job-offer-index.component';
import {JobOfferEditComponent} from './job-offer/job-offer-edit/job-offer-edit.component';
import {BenefitModule} from './benefit/benefit.module';
import {CategoryModule} from './category/category.module';
import {LocationModule} from './location/location.module';
import {JobOfferModule} from './job-offer/job-offer.module';
import {TechnologyModule} from './technology/technology.module';

@NgModule({
  declarations: [
    AppComponent,
    AuthComponent,
    PasswordResetComponent,
    SignInComponent,
    BenefitComponent,
    CategoryComponent,
    LocationComponent,
    SeniorityComponent,
    TechnologyComponent,
    UserComponent,
    BenefitEditComponent,
    BenefitIndexComponent,
    BenefitNewComponent,
    CategoryIndexComponent,
    CategoryEditComponent,
    CategoryNewComponent,
    LocationIndexComponent,
    LocationEditComponent,
    LocationNewComponent,
    SeniorityIndexComponent,
    SeniorityNewComponent,
    SeniorityEditComponent,
    TechnologyIndexComponent,
    TechnologyEditComponent,
    TechnologyNewComponent,
    JobOfferComponent,
    JobOfferNewComponent,
    JobOfferIndexComponent,
    JobOfferEditComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    UserModule,
    BenefitModule,
    CategoryModule,
    LocationModule,
    JobOfferModule,
    TechnologyModule,

  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
