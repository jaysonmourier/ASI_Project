import { Component } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Article} from "../Modele/article.modele";
@Component({
  selector: 'app-admin-delete',
  templateUrl: './admin-delete.component.html',
  styleUrls: ['./admin-delete.component.css']
})
export class AdminDeleteComponent {

  articles: Article[] = [];
  constructor(private http: HttpClient) {
    console.log("loaded");
  }

  ngOnInit() {
    this.http.get<Article[]>('http://localhost:8080/ASI_Project_war/api/articles')
      .subscribe((response: Article[]) => {
        this.articles = response;
      });
  }

  retirerDuPanier(articleId: number) {
    // Envoyer une requête HTTP DELETE à l'API REST
    this.http.delete('http://localhost:8080/ASI_Project_war/api/articles/' + articleId).subscribe(() => {
      location.reload();
    }, error => {
      console.log(articleId);
      console.error(error);
    });
  }


}
