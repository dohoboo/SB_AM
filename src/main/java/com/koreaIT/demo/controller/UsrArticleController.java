package com.koreaIT.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.koreaIT.demo.vo.Article;

@Controller
public class UsrArticleController {

	int lastArticleId;
	private List<Article> articles;

	public UsrArticleController() {
		lastArticleId = 0;
		articles = new ArrayList<>();

		makeTestData();
	}

	private void makeTestData() {

		for (int i = 1; i <= 10; i++) {
			String title = "제목" + i;
			String body = "내용" + i;

			wirteAritcle(title, body);
		}
	}

	private Article wirteAritcle(String title, String body) {

		int id = lastArticleId + 1;
		lastArticleId = id;

		Article article = new Article(id, title, body);

		articles.add(article);

		return article;
	}

	private Article getArticleById(int id) {

		for (Article article : articles) {
			if (article.getId() == id) {
				return article;
			}
		}
		return null;
	}

	@RequestMapping("/usr/article/doAdd")
	@ResponseBody
	public Article doAdd(String title, String body) {

		Article article = wirteAritcle(title, body);

		return article;
	}

	@RequestMapping("/usr/article/doDelete")
	@ResponseBody
	public String doDelete(int id) {

		Article article = getArticleById(id);

		if (article == null) {
			return id + "번 게시글은 존재하지 않습니다.";
		}
		articles.remove(article);
		return id + "번 게시글이 삭제되었습니다.";
	}

	@RequestMapping("/usr/article/doModify")
	@ResponseBody
	public String doModify(int id, String title, String body) {

		Article foundArticle = null;
		for (Article article : articles) {
			if (article.getId() == id) {
				foundArticle = article;
				break;
			}
			if (foundArticle == null) {
				return id + "번 게시글은 존재하지 않습니다.";
			}
			foundArticle = wirteAritcle(title, body);
		}
		return id + "번 게시글이 수정되었습니다.";
	}

	@RequestMapping("/usr/article/getArticles")
	@ResponseBody
	public List<Article> getArticles() {

		return articles;
	}
}
