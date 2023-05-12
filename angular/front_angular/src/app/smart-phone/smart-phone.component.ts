import {Component, OnInit} from '@angular/core';
import {Article} from "../Modele/article.modele";
import {HttpClient} from "@angular/common/http";

@Component({
  selector: 'app-smart-phone',
  templateUrl: './smart-phone.component.html',
  styleUrls: ['./smart-phone.component.css']
})
export class SmartPhoneComponent implements OnInit {
  articles: Article[] = [];

  articleAjoute: boolean =false;
  constructor(private http: HttpClient) {
    console.log("loaded");
  }

  ngOnInit() {
    this.http.get<Article[]>('http://localhost:8080/ASI_Project_war/api/articles/category/8')
      .subscribe((response: Article[]) => {
        this.articles = response;
      });
  }

  ajouterAuPanier(article: Article) {
    // Envoyer une requête HTTP POST à l'API REST
    this.http.post('http://localhost:8080/ASI_Project_war/api/panier/ajouter/'+ article.id +'/1', this.articles).subscribe(() => {
      this.articleAjoute=true;
      setTimeout(() => {
        this.articleAjoute = false;
      }, 2000);
    }, error => {
      console.error(error);
    });
  }
}
