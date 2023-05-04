import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ConnexionComponent } from './connexion/connexion.component';
import { DisqueComponent } from './disque/disque.component';
import { HomeComponent } from './home/home.component';
import { PcAccessoiresComponent } from './pc-accessoires/pc-accessoires.component';
import { PcBureauComponent } from './pc-bureau/pc-bureau.component';
import { PcPortableComponent } from './pc-portable/pc-portable.component';
import { SmartPhoneComponent } from './smart-phone/smart-phone.component';
import { StockAccessoiresComponent } from './stock-accessoires/stock-accessoires.component';
import { TelAccessoiresComponent } from './tel-accessoires/tel-accessoires.component';
import { TelFixeComponent } from './tel-fixe/tel-fixe.component';
import { USBComponent } from './usb/usb.component';
import {PanierComponent} from "./panier/panier.component";

const routes: Routes = [
  {path:'',component: HomeComponent},
  {path:'PcPortable',component: PcPortableComponent},
  {path:'pc-bureau',component: PcBureauComponent},
  {path:'PcAccessoires',component: PcAccessoiresComponent},
  {path:'Connexion',component: ConnexionComponent},
  {path:'SmartPhone',component: SmartPhoneComponent},
  {path:'TelFixe',component: TelFixeComponent},
  {path:'TelAccessoires',component: TelAccessoiresComponent},
  {path:'Disque',component: DisqueComponent},
  {path:'USB',component: USBComponent},
  {path:'StockAccessoires',component: StockAccessoiresComponent},
  {path:'Panier',component: PanierComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
