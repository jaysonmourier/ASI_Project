import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NavBarComponent } from './nav-bar/nav-bar.component';
import { HomeComponent } from './home/home.component';
import { PcPortableComponent } from './pc-portable/pc-portable.component';
import { PcBureauComponent } from './pc-bureau/pc-bureau.component';
import { PcAccessoiresComponent } from './pc-accessoires/pc-accessoires.component';
import { ConnexionComponent } from './connexion/connexion.component';
import { SmartPhoneComponent } from './smart-phone/smart-phone.component';
import { TelFixeComponent } from './tel-fixe/tel-fixe.component';
import { TelAccessoiresComponent } from './tel-accessoires/tel-accessoires.component';
import { DisqueComponent } from './disque/disque.component';
import { USBComponent } from './usb/usb.component';
import { StockAccessoiresComponent } from './stock-accessoires/stock-accessoires.component';

@NgModule({
  declarations: [
    AppComponent,
    NavBarComponent,
    HomeComponent,
    PcPortableComponent,
    PcBureauComponent,
    PcAccessoiresComponent,
    ConnexionComponent,
    SmartPhoneComponent,
    TelFixeComponent,
    TelAccessoiresComponent,
    DisqueComponent,
    USBComponent,
    StockAccessoiresComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
