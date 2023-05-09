import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Article } from "../Modele/article.modele";

@Component({
  selector: 'app-pc-portable',
  templateUrl: './pc-portable.component.html',
  styleUrls: ['./pc-portable.component.css']
})
export class PcPortableComponent implements OnInit {
  articles: Article[] = [];
  articleAjoute: boolean= false;

  constructor(private http: HttpClient) {
    console.log("loaded");
  }

  ngOnInit() {
    this.http.get<Article[]>('http://localhost:8080/ASI_Project_war/api/articles/category/2')
      .subscribe((response: Article[]) => {
        this.articles = response;
      });
  }

  ajouterAuPanier(article: Article) {
    // Envoyer une requête HTTP POST à l'API REST
    this.http.post('http://localhost:8080/ASI_Project_war/api/panier/ajouter/'+ article.id +'/1', this.articles).subscribe(() => {
      this.articleAjoute = true;
      setTimeout(() => {
        this.articleAjoute = false;
      }, 2000);
    }, error => {
      console.error(error);
    });
  }
}
